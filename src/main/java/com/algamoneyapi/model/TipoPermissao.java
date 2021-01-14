package com.algamoneyapi.model;

public enum TipoPermissao {

	ROLE_CADASTRAR_CATEGORIA("Cadastrar Categoria"),
	ROLE_PESQUISAR_CATEGORIA("Pesquisar Categoria"),
	ROLE_REMOVER_CATEGORIA("Remover Categoria"),
	
	ROLE_CADASTRAR_PESSOA("Cadastrar Pessoa"),
	ROLE_PESQUISAR_PESSOA("Pesquisar Pessoa"),
	ROLE_REMOVER_PESSOA("Remover Pessoa"),
	
	ROLE_CADASTRAR_LANCAMENTO("Cadastrar Lançamento"),
	ROLE_REMOVER_LANCAMENTO("Remover Lancaçamento"),
	ROLE_PESQUISAR_LANCAMENTO("Pesquisar Lançamento"),
	
	ROLE_PESQUISAR_USUARIO("Pesquisar Usuário"),
	ROLE_CADASTRAR_USUARIO("Cadastrar Usuário"),
	ROLE_REMOVER_USUARIO("Remover Usuário"),
	
	ROLE_CADASTRAR_SENHA("Alterar Senha");
	
	
	private String descricao;
	
	TipoPermissao(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
