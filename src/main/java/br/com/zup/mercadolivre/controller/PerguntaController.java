package br.com.zup.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.mercadolivre.component.Email;
import br.com.zup.mercadolivre.config.UsuarioLogado;
import br.com.zup.mercadolivre.controller.request.NovaPerguntaRequest;
import br.com.zup.mercadolivre.model.Pergunta;
import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;

@RestController
public class PerguntaController {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private Email email;
	
	@PostMapping(value = "/produtos/{id}/perguntas")
	@Transactional
	public ResponseEntity<?> perguntar(@PathVariable Long id, @Valid @RequestBody NovaPerguntaRequest request, @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
		
		Usuario usuario = usuarioLogado.get();
		Produto produto = manager.find(Produto.class, id);
		
		if(produto.pertenceUsuario(usuario)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "O dono do produto não pode perguntar para ele próprio.");
		}
		
		Pergunta pergunta = request.toModel(usuario, produto);
		
		manager.persist(pergunta);
		
		email.enviarEmail(pergunta);
		
		
		return ResponseEntity.ok(manager.createQuery("select p from Pergunta p").getResultList().toArray());
	}
}
