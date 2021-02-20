package br.com.zup.mercadolivre.controller.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import br.com.zup.mercadolivre.model.Caracteristica;
import br.com.zup.mercadolivre.model.Produto;

public class NovaCaracteristicaRequest {

	@NotBlank
	private String nome;
	@NotBlank
	private String descricao;
	
	public NovaCaracteristicaRequest(@NotBlank String nome, @NotBlank String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}
	
	public Caracteristica toModel(@Valid Produto produto) {
		return new Caracteristica(nome, descricao, produto);
	}
}
