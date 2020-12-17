package com.algamoneyapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.algamoneyapi.model.Categoria;
import com.algamoneyapi.repository.categoria.CategoriaRepositoryQuery;
import com.algamoneyapi.repository.filter.CategoriaFilter;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>, CategoriaRepositoryQuery{
	
	Page<Categoria> filtrar(CategoriaFilter categoriaFilter, Pageable pageable);

}