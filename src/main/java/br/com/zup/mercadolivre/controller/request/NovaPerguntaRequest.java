package br.com.zup.mercadolivre.controller.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.mercadolivre.model.Pergunta;
import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;

public class NovaPerguntaRequest {

	@NotBlank
	private String titulo;
	
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public Pergunta toModel(@NotNull @Valid Usuario usuario, @NotNull @Valid Produto produto) {
		return new Pergunta(titulo, usuario, produto);
	}


	public String getTitulo() {
		return titulo;
	}

	
	
}
