package br.com.zup.mercadolivre.controller;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.mercadolivre.config.UsuarioLogado;
import br.com.zup.mercadolivre.controller.request.NovaImagemRequest;
import br.com.zup.mercadolivre.controller.request.NovoProdutoRequest;
import br.com.zup.mercadolivre.model.Produto;

@RestController
public class ProdutoController {

	
	@Autowired
	private UploaderFake uploaderFake;
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping(value = "/produtos")
	@Transactional
	public String criar(@RequestBody @Valid NovoProdutoRequest request, @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
		
		if(usuarioLogado != null) {
			Produto produto = request.toModel(manager, usuarioLogado.get());
			manager.persist(produto);			
			return produto.toString();
		} else {
			System.out.println("usuario nulo");
		}
		return "nada retornado";
	}
	
	@PostMapping(value = "/produtos/{id}/imagens")
	@Transactional
	public String uploadImagem(@PathVariable Long id, @Valid NovaImagemRequest request, @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
				
		Set<String> links = uploaderFake.envia(request.getUriImagens());
		Produto produto = manager.find(Produto.class, id);
		produto.associaUriImagens(links);
		
		if(!produto.pertenceUsuario(usuarioLogado.get())) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		
		manager.merge(produto);
		return produto.toString();
	}
}
