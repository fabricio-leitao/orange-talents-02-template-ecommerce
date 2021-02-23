package br.com.zup.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.config.UsuarioLogado;
import br.com.zup.mercadolivre.controller.request.NovaOpiniaoRequest;
import br.com.zup.mercadolivre.model.Opiniao;
import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;

@RestController
public class OpiniaoController {

	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping(value = "/produtos/{id}/opinioes")
	@Transactional
	public String opinar(@PathVariable Long id, @RequestBody NovaOpiniaoRequest request, @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
		Produto produto = manager.find(Produto.class, id);
		Usuario usuario = usuarioLogado.get();
		
		Opiniao opiniao = request.toModel(produto, usuario);
		
		manager.persist(opiniao);
		
		return opiniao.toString();
	}
}
