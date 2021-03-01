package br.com.zup.mercadolivre.fechamentoCompra;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FechamentoCompraParte2Controller {

	@PersistenceContext
	private EntityManager manager;
	//@Autowired
	//private NotaFiscal notaFiscal;
	//@Autowired
	//private Ranking ranking;
	
	@Autowired
	private EventosNovaCompra eventosNovaCompra;
	
	@PostMapping("/retorno-pagseguro/{id}")
	@Transactional
	public String retornoPagSeguro(@PathVariable Long id,@Valid @RequestBody RetornoPagSeguroRequest request) {
	
		return processa(id, request);
	}
	
	@PostMapping("/retorno-paypal/{id}")
	@Transactional
	public String retornoPaypal(@PathVariable Long id,@Valid @RequestBody RetornoPaypalRequest request) {
	
		return processa(id, request);
	}
	
	private String processa(Long idCompra, RetornoPagamento retornoPagamento) {
		Compra compra = manager.find(Compra.class, idCompra);
		compra.adicionaTransacao(retornoPagamento);
		manager.merge(compra);
		
		//if(compra.processadaComSucesso()) {
			//notaFiscal.processa(compra);
			//ranking.processa(compra);
			//emailSucesso.processa(compra);
			eventosNovaCompra.processa(compra);
		//}
		
		return compra.toString();
	}
}
