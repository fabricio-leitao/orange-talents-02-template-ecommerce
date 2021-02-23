package br.com.zup.mercadolivre.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;
import org.springframework.context.annotation.Primary;

@Entity
@Primary
public class Imagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Valid
	@ManyToOne
	private Produto produto;
	@NotBlank
	@URL
	private String uriImagem;
	
	@Deprecated
	public Imagem() {
		
	}
	
	public Imagem(@NotNull @Valid Produto produto, @NotBlank @URL String uriImagem) {
		this.produto = produto;
		this.uriImagem = uriImagem;
	}

	@Override
	public String toString() {
		return "Imagem [id=" + id + ", uriImage=" + uriImagem + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((uriImagem == null) ? 0 : uriImagem.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Imagem other = (Imagem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (uriImagem == null) {
			if (other.uriImagem != null)
				return false;
		} else if (!uriImagem.equals(other.uriImagem))
			return false;
		return true;
	}

	public String getUriImagem() {
		return this.uriImagem;
	}
	
	
}
