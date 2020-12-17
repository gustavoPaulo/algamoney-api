package com.algamoneyapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algamoneyapi.model.Categoria;
import com.algamoneyapi.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	public Categoria atualizar(Long codigo, Categoria categoria) {
		
		Categoria categoriaSalva = buscarCategoriaPeloCodigo(codigo);
		BeanUtils.copyProperties(categoria, categoriaSalva, "codigo");
		return this.categoriaRepository.save(categoriaSalva);
	}

	public Categoria buscarCategoriaPeloCodigo(Long codigo) {
		
		Categoria categoriaSalva = this.categoriaRepository.findById(codigo).orElseThrow(
				() -> new EmptyResultDataAccessException(1));
		return categoriaSalva;
	}

}
