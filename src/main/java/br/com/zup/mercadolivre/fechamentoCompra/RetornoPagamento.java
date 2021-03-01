package br.com.zup.mercadolivre.fechamentoCompra;

public interface RetornoPagamento {

	Transacao toTransacao(Compra compra);
}
