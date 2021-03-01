package br.com.zup.mercadolivre.fechamentoCompra;

public enum StatusRetorno {
	SUCESSO, ERRO;

	public StatusTransacao normaliza() {
		if(this.equals(SUCESSO)) {
			return StatusTransacao.SUCESSO;
		}
		return StatusTransacao.ERRO;
	}
}
