package br.com.zup.mercadolivre.controller.request;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zup.mercadolivre.model.Opiniao;
import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;

public class NovaOpiniaoRequest {

	@NotNull
	@Min(value = 1)
	@Max(value = 5)
	private Integer nota;
	@NotBlank
	private String titulo;
	@Column(columnDefinition = "TEXT")
	@NotBlank
	@Size(max = 500)
	private String descricao;
	public NovaOpiniaoRequest(@NotNull @Min(1) @Max(5) Integer nota, @NotBlank String titulo,
			@NotBlank @Size(max = 500) String descricao) {
		super();
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
	}
	public Opiniao toModel(Produto produto, Usuario usuario) {
		return new Opiniao(nota, titulo, descricao, produto, usuario);
	}
	
	
}
