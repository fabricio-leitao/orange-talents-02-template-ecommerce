package br.com.zup.mercadolivre.controller.request;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class NovaImagemRequest {

	@Size(min = 1)
	@NotNull
	private List<MultipartFile> uriImagens = new ArrayList<>();

	public void setUriImagens(List<MultipartFile> uriImagens) {
		this.uriImagens = uriImagens;
	}

	public List<MultipartFile> getUriImagens() {
		return uriImagens;
	}

	
	
	
}
