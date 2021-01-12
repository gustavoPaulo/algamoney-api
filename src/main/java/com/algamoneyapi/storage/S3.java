package com.algamoneyapi.storage;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.algamoneyapi.config.property.AlgamoneyApiProperty;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GroupGrantee;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.ObjectTagging;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.SetObjectTaggingRequest;
import com.amazonaws.services.s3.model.Tag;

@Component
public class S3 {

	@Autowired
	private AmazonS3 amazonS3;
	
	@Autowired
	private AlgamoneyApiProperty property;
	
	private static final Logger logger = LoggerFactory.getLogger(S3.class);
	
	
	public String salvarTemporariamente(MultipartFile arquivo) {
		
		AccessControlList acl = new AccessControlList();
		acl.grantPermission(GroupGrantee.AllUsers, Permission.Read);
		
		ObjectMetadata objectMetaData = new ObjectMetadata();
		objectMetaData.setContentType(arquivo.getContentType());
		objectMetaData.setContentLength(arquivo.getSize());
		
		String nomeUnico = gerarNomeUnico(arquivo.getOriginalFilename());
		
		try {
			PutObjectRequest putObjectRequest = new PutObjectRequest(
					property.getS3().getBucket(),
					nomeUnico,
					arquivo.getInputStream(),
					objectMetaData)
					.withAccessControlList(acl);
			
			putObjectRequest.setTagging(new ObjectTagging(
					Arrays.asList((new Tag("expirar", "true")))));
			
			amazonS3.putObject(putObjectRequest);
			
			if (logger.isDebugEnabled()) {
				logger.debug("Arquivo {} enviado com sucesso para o S3.", 
						arquivo.getOriginalFilename());
			}
			
			return nomeUnico;
			
		} catch (IOException e) {
			throw new RuntimeException("Problemas ao tentar enviar o arquivo para o S3.", e);
		}
	}

	private String gerarNomeUnico(String originalFilename) {
		return UUID.randomUUID().toString() + "_" + originalFilename;
	}
	
	public String configurarUrl(String objeto) {
		return "https:////" + property.getS3().getBucket() +
				".s3.amazonaws.com/" + objeto;
	}

	@SuppressWarnings("unchecked")
	public void salvarAnexo(String objeto) {
		
		SetObjectTaggingRequest setObjectTaggingRequest = new SetObjectTaggingRequest(
				property.getS3().getBucket(),
				objeto,
				new ObjectTagging(Collections.EMPTY_LIST));
		
		amazonS3.setObjectTagging(setObjectTaggingRequest);
	}

	public void remover(String objeto) {
		
		DeleteObjectRequest deleteObjectResquest = new DeleteObjectRequest(
				property.getS3().getBucket(), objeto);
		
		amazonS3.deleteObject(deleteObjectResquest);
	}

	public void substituir(String objetoAntigo, String objetoNovo) {
		
		if (StringUtils.hasText(objetoAntigo)) {
			this.remover(objetoAntigo);
		}
		
		salvarAnexo(objetoNovo);
	}
	
}
