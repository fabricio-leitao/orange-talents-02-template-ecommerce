package br.com.zup.mercadolivre.controller.view;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import br.com.zup.mercadolivre.controller.util.Opinioes;
import br.com.zup.mercadolivre.model.Produto;

public class ProdutoDetalhadoView {

	private String nome;
	private BigDecimal preco;
	private String descricao;
	private Set<CaracteristicaProdutoDetalhado> caracteristicas;
	private Set<String> uriImagens;
	private SortedSet<String> perguntas;
	private Set<Map<String, String>> opinioes;
	private double mediaNotas;
	private int total;
	
	
	public ProdutoDetalhadoView(Produto produto) {
		this.nome = produto.getNome();
		this.preco = produto.getValor();
		this.descricao = produto.getDescricao();
		this.caracteristicas = produto.mapeiaCaracteristicas(CaracteristicaProdutoDetalhado::new);
		this.uriImagens = produto.mapeiaImagens(imagem -> imagem.getUriImagem());
		this.perguntas = produto.mapeiaPerguntas(pergunta -> pergunta.getTitulo());
		
		Opinioes opinioes = produto.getOpinioes();
		
		
		this.opinioes = opinioes.mapeiaOpinioes(opiniao -> {
			return Map.of("titulo", opiniao.getTitulo(), "descricao", opiniao.getDescricao());
		});
		
		this.mediaNotas = opinioes.media();
		this.total = opinioes.total();
		
	}

	public int getTotal() {
		return total;
	}
	
	public double getMediaNotas() {
		return mediaNotas;
	}
	
	public String getNome() {
		return nome;
	}


	public BigDecimal getPreco() {
		return preco;
	}


	public String getDescricao() {
		return descricao;
	}


	public Set<CaracteristicaProdutoDetalhado> getCaracteristicas() {
		return caracteristicas;
	}


	public Set<String> getUriImagens() {
		return uriImagens;
	}


	public Set<String> getPerguntas() {
		return perguntas;
	}


	public Set<Map<String, String>> getOpinioes() {
		return opinioes;
	}
	
	
}
