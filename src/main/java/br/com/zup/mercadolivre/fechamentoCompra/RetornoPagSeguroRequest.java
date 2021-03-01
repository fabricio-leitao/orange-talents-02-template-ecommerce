package br.com.zup.mercadolivre.fechamentoCompra;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RetornoPagSeguroRequest implements RetornoPagamento{

	@NotBlank
	private String idTransacao;
	@NotNull
	private StatusRetorno status;
	public RetornoPagSeguroRequest(@NotBlank String idTransacao, @NotNull StatusRetorno status) {
		super();
		this.idTransacao = idTransacao;
		this.status = status;
	}
	@Override
	public String toString() {
		return "RetornoPagSeguroRequest [idTransacao=" + idTransacao + ", status=" + status + "]";
	}
	public Transacao toTransacao(Compra compra) {
		return new Transacao(status.normaliza(), idTransacao, compra);
	}
}
