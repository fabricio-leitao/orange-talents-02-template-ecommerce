package br.com.zup.mercadolivre.fechamentoCompra;

import org.springframework.web.util.UriComponentsBuilder;

public enum PagamentoEscolhido {
	PAGSEGURO {
		@Override
		String criaUrlRetorno(Compra compra, UriComponentsBuilder uriComponentsBuilder) {
			String urlRetornoPagseguro = uriComponentsBuilder.path("/retorno-pagseguro/{id}")
					.buildAndExpand(compra.getId()).toString();

			return "pagseguro.com/" + compra.getId() + "?redirectUrl=" + urlRetornoPagseguro;
		}
	},
	PAYPAL {
		@Override
		String criaUrlRetorno(Compra compra, UriComponentsBuilder uriComponentsBuilder) {
			String urlRetornoPaypal = uriComponentsBuilder.path("/retorno-paypal/{id}").buildAndExpand(compra.getId())
					.toString();

			return "paypal.com/" + compra.getId() + "?redirectUrl=" + urlRetornoPaypal;
		}
	};

	abstract String criaUrlRetorno(Compra compra, UriComponentsBuilder uriComponentsBuilder);
}
