package com.algamoneyapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.algamoneyapi.model.Pessoa;
import com.algamoneyapi.repository.filter.PessoaFilter;
import com.algamoneyapi.repository.pessoa.PessoaRepositoryQuery;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>, PessoaRepositoryQuery{

	Page<Pessoa> filtrar(PessoaFilter pessoaFilter, Pageable pageable);

}