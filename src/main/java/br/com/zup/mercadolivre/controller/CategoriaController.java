package br.com.zup.mercadolivre.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.controller.request.NovaCategoriaRequest;
import br.com.zup.mercadolivre.model.Categoria;
import br.com.zup.mercadolivre.repository.CategoriaRepository;

@RestController
public class CategoriaController {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private CategoriaRepository repository;
	
	@PostMapping(value = "/categorias")
	@Transactional
	public String criar(@RequestBody @Valid NovaCategoriaRequest request) {
		Categoria categoria = request.toModel(manager);
		manager.persist(categoria);
		
		return categoria.toString();
	}
	
	@GetMapping(value = "/categorias")
	public ResponseEntity<List<Categoria>> listar() {
		List<Categoria> categorias = repository.findAll();
		return ResponseEntity.ok(categorias);
	}
}
