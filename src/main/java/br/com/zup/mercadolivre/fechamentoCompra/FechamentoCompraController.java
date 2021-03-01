package br.com.zup.mercadolivre.fechamentoCompra;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.mercadolivre.component.Email;
import br.com.zup.mercadolivre.config.UsuarioLogado;
import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;

@RestController
public class FechamentoCompraController {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private Email email;
	
	@PostMapping(value = "/compras")
	@Transactional
	public String comprar(@RequestBody @Valid NovaCompraRequest request, @AuthenticationPrincipal UsuarioLogado usuarioLogado, UriComponentsBuilder uri) throws BindException {
		
		Produto produto = manager.find(Produto.class, request.getIdProduto());
		
		int quantidade = request.getQuantidade();
		boolean estoque = produto.abateEstoque(quantidade);
		
		if(estoque) {
			Usuario comprador = usuarioLogado.get();
			Compra compra = request.toModel(manager, produto, comprador);
			manager.persist(compra);
			email.confirmarCompra(compra);
			
			return compra.urlRedirecionamento(uri);
			
		} 
		
		BindException problemaComEstoque = new BindException(request, "novaCompraRequest");
		problemaComEstoque.reject(null, "Não foi possível realizar compra por conta do estoque.");
		
		throw problemaComEstoque;
	}
	
}
