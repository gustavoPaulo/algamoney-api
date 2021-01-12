package com.algamoneyapi.config.mail;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.algamoneyapi.model.Lancamento;
import com.algamoneyapi.model.Usuario;

//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.event.EventListener;
//import com.algamoneyapi.model.Lancamento;
//import com.algamoneyapi.repository.LancamentoRepository;
//import java.util.Arrays;
//import java.util.HashMap;

@Component
public class Mailer {

	private static final String TEMPLATE_EMAIL = "mail/aviso-lancamentos-vencidos";
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private TemplateEngine thymeleaf;
	
//	@Autowired
//	private LancamentoRepository repo;
//	
//	
//	@EventListener
//	public void testeEmail(ApplicationReadyEvent event) {
//		
//		List<Lancamento> lista = repo.findAll();
//		
//		Map<String, Object> variaveis = new HashMap<>();
//		variaveis.put("lancamentos", lista);
//		
//		try {
//		
//			this.enviarEmail("gustavodevjava@gmail.com", 
//					 Arrays.asList("gustavo-tj@hotmail.com"), 
//					 "Testando e-mail com Java 8", 
//					 TEMPLATE_EMAIL,
//					 variaveis);
//	
//			System.out.println("LOG --> E-mail enviado com sucesso!");
//			
//		} catch (Exception e) {
//			System.err.println("Ocorreu um erro ao enviar o e-mail --> " + e);
//		}
//	}
	
	public void enviarEmail(String remetente, List<String> destinatarios, String assunto, String mensagem) {
		
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
			
			helper.setFrom(remetente);
			helper.setTo(destinatarios.toArray(new String[destinatarios.size()]));
			helper.setSubject(assunto);
			helper.setText(mensagem, true);
			
			mailSender.send(mimeMessage);
			
		} catch (MessagingException e) {
			throw new RuntimeException("Problemas no envio de e-mail!", e);
		}
	}
	
	public void enviarEmail(String remetente, List<String> destinatarios, String assunto, 
			String template, Map<String, Object> variaveis) {
		
		Context context = new Context(new Locale("pt", "BR"));
		
		variaveis.entrySet().forEach(e -> context.setVariable(e.getKey(), e.getValue()));
		
		String mensagem = thymeleaf.process(template, context);
		
		this.enviarEmail(remetente, destinatarios, assunto, mensagem);
	}
	
	public void avisarSobreLancamentosVencidos(List<Lancamento> vencidos, List<Usuario> destinatarios) {
		
		Map<String, Object> variaveis = new HashMap<>();
		variaveis.put("lancamentos", vencidos);
		
		List<String> emails = destinatarios.stream()
				.map(u -> u.getEmail())
				.collect(Collectors.toList());
		
		this.enviarEmail("gustavodevjava@gmail.com", 
						 emails, 
						 "Lançamentos vencidos", 
						 TEMPLATE_EMAIL,
						 variaveis);
	}

}
