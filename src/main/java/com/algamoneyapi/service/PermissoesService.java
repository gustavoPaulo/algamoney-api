package com.algamoneyapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algamoneyapi.model.Permissoes;
import com.algamoneyapi.repository.PermissoesRepository;

@Service
public class PermissoesService {

	@Autowired
	private PermissoesRepository permissoesRepository;
	
	
	public List<Permissoes> listarTodas() {
		
		List<Permissoes> todasPermissoes = permissoesRepository.findAll();
		
		return todasPermissoes;
	}

}
