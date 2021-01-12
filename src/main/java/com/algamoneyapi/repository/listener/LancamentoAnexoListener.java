package com.algamoneyapi.repository.listener;

import javax.persistence.PostLoad;

import org.springframework.util.StringUtils;

import com.algamoneyapi.AlgamoneyApiApplication;
import com.algamoneyapi.model.Lancamento;
import com.algamoneyapi.storage.S3;

public class LancamentoAnexoListener {

	@PostLoad
	public void postLead(Lancamento lancamento) {
		
		if (StringUtils.hasText(lancamento.getAnexo())) {
			S3 s3 = AlgamoneyApiApplication.getBean(S3.class);
			
			lancamento.setUrlAnexo(s3.configurarUrl(lancamento.getAnexo()));
		}
	}
	
}
