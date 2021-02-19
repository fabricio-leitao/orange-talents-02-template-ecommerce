package br.com.zup.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.controller.request.NovoUsuarioRequest;
import br.com.zup.mercadolivre.model.Usuario;

@RestController
public class CadastraUsuarioController {

	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping(value = "/usuarios")
	@Transactional
	public void criar(@RequestBody @Valid NovoUsuarioRequest request) {
		Usuario usuario = request.toModel();
		manager.persist(usuario);
	}
}
