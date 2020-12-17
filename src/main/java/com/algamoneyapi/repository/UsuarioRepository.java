package com.algamoneyapi.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.algamoneyapi.model.Usuario;
import com.algamoneyapi.repository.filter.UsuarioFilter;
import com.algamoneyapi.repository.usuario.UsuarioRepositoryQuery;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryQuery{

	public Optional<Usuario> findByEmail(String email);
	
	Page<Usuario> filtrar(UsuarioFilter usuarioFilter, Pageable pageable);
}