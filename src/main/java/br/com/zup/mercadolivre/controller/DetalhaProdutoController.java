package br.com.zup.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.controller.view.ProdutoDetalhadoView;
import br.com.zup.mercadolivre.model.Produto;

@RestController
public class DetalhaProdutoController {

	@PersistenceContext
	private EntityManager manager;
	
	@GetMapping(value = "/produtos/{id}")
	public ProdutoDetalhadoView listar(@PathVariable Long id) {
		Produto produto = manager.find(Produto.class, id);
		return new ProdutoDetalhadoView(produto);
	}
}
