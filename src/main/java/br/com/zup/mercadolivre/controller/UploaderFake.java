package br.com.zup.mercadolivre.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UploaderFake {

	public Set<String> envia(List<MultipartFile> uriImagens) {
	
		return uriImagens.stream()
				.map(imagem -> "http://bucket.io/" 
						+ imagem.getOriginalFilename())
				.collect(Collectors.toSet());
	}

}
