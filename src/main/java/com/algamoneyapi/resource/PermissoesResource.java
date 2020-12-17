package com.algamoneyapi.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algamoneyapi.model.Permissoes;
import com.algamoneyapi.service.PermissoesService;

@RestController
@RequestMapping("/permissoes")
public class PermissoesResource {

	@Autowired
	private PermissoesService permissoesService;
	
	
	@GetMapping
	public List<Permissoes> listar(){
		
		return permissoesService.listarTodas();
	}
}
