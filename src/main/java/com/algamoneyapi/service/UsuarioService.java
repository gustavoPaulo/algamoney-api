package com.algamoneyapi.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.algamoneyapi.model.Permissoes;
import com.algamoneyapi.model.Usuario;
import com.algamoneyapi.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	

	public Usuario atualizar(Long codigo, Usuario usuario) {

		Usuario usuarioSalvo = buscarUsuarioPeloCodigo(codigo);
		BeanUtils.copyProperties(usuario, usuarioSalvo, "codigo");
		return this.usuarioRepository.save(usuarioSalvo);
	}

	public Usuario buscarUsuarioPeloCodigo(Long codigo) {
		Usuario usuarioSalvo = this.usuarioRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return usuarioSalvo;
	}

	public Usuario salvar(@Valid Usuario usuario) {
		
		Permissoes permissao = new Permissoes();
		permissao.setCodigo(13L);
		List<Permissoes> permissoes = new ArrayList<Permissoes>();
		permissoes.add(permissao);
		
		if (usuario.getPermissoes() == null) { 
			usuario.setPermissoes(permissoes);
		}
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		usuario.setSenha(encoder.encode(usuario.getSenha()));

		Usuario usuarioSalvo = usuarioRepository.save(usuario);

		return usuarioSalvo;
	}
	
	public Usuario alterarSenha(Long codigo, Usuario usuario) {
		
		Usuario usuarioSalvo = buscarUsuarioPeloCodigo(codigo);
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		boolean senhasIguais = encoder.matches(usuario.getSenha(), usuarioSalvo.getSenha());
		
		if (!senhasIguais) {
			usuario.setSenha(encoder.encode(usuario.getSenha()));

			BeanUtils.copyProperties(usuario, usuarioSalvo, "codigo");
			return this.usuarioRepository.save(usuarioSalvo);
			
		} else {
			return null;
		}
	}

}
