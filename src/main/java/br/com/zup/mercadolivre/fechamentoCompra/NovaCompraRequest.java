package br.com.zup.mercadolivre.fechamentoCompra;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;

public class NovaCompraRequest {

	@NotNull
	@Positive
	private int quantidade;
	@NotNull
	private PagamentoEscolhido pagamento;
	@NotNull
	private Long idProduto;
	
	public NovaCompraRequest(@NotNull @Positive int quantidade, @NotNull PagamentoEscolhido pagamento, 
			@Valid @NotNull Long idProduto) {
		super();
		this.quantidade = quantidade;
		this.pagamento = pagamento;
		this.idProduto = idProduto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public PagamentoEscolhido getPagamento() {
		return pagamento;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public Compra toModel(EntityManager manager, Produto produtoEscolhido, Usuario comprador) {
		@NotNull Produto produto = manager.find(Produto.class, idProduto);
		
		return new Compra(quantidade, pagamento, produto, comprador);
	}
	
	
}
