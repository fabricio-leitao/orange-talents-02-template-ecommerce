package br.com.zup.mercadolivre.controller.view;

import br.com.zup.mercadolivre.model.Caracteristica;

public class CaracteristicaProdutoDetalhado {

	private String nome;
	private String descricao;

	public CaracteristicaProdutoDetalhado(Caracteristica caracteristica) {
		this.nome = caracteristica.getNome();
		this.descricao = caracteristica.getDescricao();
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

}
