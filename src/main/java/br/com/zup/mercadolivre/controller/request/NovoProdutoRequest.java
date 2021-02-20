package br.com.zup.mercadolivre.controller.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import br.com.zup.mercadolivre.model.Categoria;
import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;

public class NovoProdutoRequest {

	@NotBlank
	private String nome;
	@NotNull
	@Positive
	private BigDecimal valor;
	@NotNull
	@Positive
	private Integer quantidade;
	@NotBlank
	@Column(columnDefinition = "TEXT")
	@Length(max = 1000)
	private String descricao;
	@PastOrPresent
	private LocalDateTime dataCadastro = LocalDateTime.now();
	@NotNull
	private Long idCategoria;
	@Size(min = 3)
	@Valid
	private Set<NovaCaracteristicaRequest> caracteristicas = new HashSet<>();
	
	public NovoProdutoRequest(@NotBlank String nome, @NotNull @Positive BigDecimal valor,
			@NotNull @Positive Integer quantidade, @NotBlank @Length(max = 1000) String descricao,
			@NotNull Long idCategoria, @Size(min = 3) @Valid Set<NovaCaracteristicaRequest> caracteristicas) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.idCategoria = idCategoria;
		this.caracteristicas.addAll(caracteristicas);
	}
	public Produto toModel(EntityManager manager, Usuario usuario) {
		@NotNull Categoria categoria = manager.find(Categoria.class, idCategoria);
		@NotNull Usuario user = manager.find(Usuario.class, usuario.getId());
		
				
		return new Produto(nome, valor, quantidade, descricao, categoria, caracteristicas, user);
	}
	
	
	
}
