package com.algamoneyapi;

import java.util.ArrayList;
import java.util.List;

import com.algamoneyapi.model.Permissoes;

public class TesteSenha {

	public static void main(String[] args) {
		
		//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		//System.out.println(encoder.encode("admin123"));
		
		Permissoes permissao = new Permissoes();
		permissao.setCodigo(13L);
		
		List<Permissoes> permissoes = new ArrayList<Permissoes>();
		permissoes.add(permissao);
		
		permissoes.forEach(p -> {
			if (p.getCodigo() == 13L) {
				System.out.println("Tem 13 na lista");
			}
		});
	}
}
