package br.com.zup.mercadolivre.controller;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.controller.request.NovoProdutoRequest;
import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;
import br.com.zup.mercadolivre.repository.UsuarioRepository;

@RestController
public class ProdutoController {

	@Autowired
	private UsuarioRepository repository;
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping(value = "/produtos")
	@Transactional
	public String criar(@RequestBody @Valid NovoProdutoRequest request) {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String email;    

		if (principal instanceof UserDetails) {
		    email = ((UserDetails)principal).getUsername();
		} else {
		    email = principal.toString();
		}
		Optional<Usuario> usuario = repository.findByEmail(email);
		if(usuario != null) {
			Produto produto = request.toModel(manager, usuario.get());
			manager.persist(produto);			
			return produto.toString();
		} else {
			System.out.println("usuario nulo");
		}
		return "nada retornado";
	}
}
