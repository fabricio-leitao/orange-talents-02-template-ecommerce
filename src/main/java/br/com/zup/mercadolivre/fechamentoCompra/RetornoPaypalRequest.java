package br.com.zup.mercadolivre.fechamentoCompra;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class RetornoPaypalRequest implements RetornoPagamento {

	@Min(0)
	@Max(1)
	private int status;
	@NotBlank
	private String idTransacao;
	public RetornoPaypalRequest(@Min(0) @Max(1) int status, @NotBlank String idTransacao) {
		super();
		this.status = status;
		this.idTransacao = idTransacao;
	}
	
	
	
	@Override
	public String toString() {
		return "RetornoPaypalRequest [status=" + status + ", idTransacao=" + idTransacao + "]";
	}



	public Transacao toTransacao(Compra compra) {
		StatusTransacao statusCalculado = this.status == 0 ? StatusTransacao.ERRO : StatusTransacao.SUCESSO;
		
		return new Transacao(statusCalculado, idTransacao, compra);
	}
}
