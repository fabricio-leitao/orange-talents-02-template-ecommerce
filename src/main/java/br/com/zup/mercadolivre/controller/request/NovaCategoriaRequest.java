package br.com.zup.mercadolivre.controller.request;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import br.com.zup.mercadolivre.model.Categoria;
import br.com.zup.mercadolivre.validator.ExistsId;
import br.com.zup.mercadolivre.validator.UniqueValue;

public class NovaCategoriaRequest {
	
	@NotBlank
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome")
	private String nome;
	@Positive
	@ExistsId(domainClass = Categoria.class, fieldName =  "id")
	private Long idCategoriaMae;
	
	public NovaCategoriaRequest(@NotBlank String nome, Long idCategoriaMae) {
		this.nome = nome;
		this.idCategoriaMae = idCategoriaMae;
	}
	public Categoria toModel(EntityManager manager) {
		Categoria categoria = new Categoria(nome);
		if(idCategoriaMae !=null) {
			Categoria catMae = manager.find(Categoria.class, idCategoriaMae);
			
			categoria.setCategoria(catMae);
		}
		return categoria;
	}
	
	
	
	
}
