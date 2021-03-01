package br.com.zup.mercadolivre.fechamentoCompra;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private StatusTransacao status;
	private @NotBlank String idTransacao;
	@NotNull
	private LocalDateTime instante;
	@ManyToOne
	private @NotNull @Valid Compra compra;

	@Deprecated
	public Transacao() {
		
	}
	
	public Transacao(@NotNull StatusTransacao status, @NotBlank String idTransacao, @NotNull @Valid Compra compra) {
		this.status = status;
		this.idTransacao = idTransacao;
		this.instante = LocalDateTime.now();
		this.compra = compra;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((compra == null) ? 0 : compra.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idTransacao == null) ? 0 : idTransacao.hashCode());
		result = prime * result + ((instante == null) ? 0 : instante.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Transacao other = (Transacao) obj;
		if (compra == null) {
			if (other.compra != null)
				return false;
		} else if (!compra.equals(other.compra))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idTransacao == null) {
			if (other.idTransacao != null)
				return false;
		} else if (!idTransacao.equals(other.idTransacao))
			return false;
		if (instante == null) {
			if (other.instante != null)
				return false;
		} else if (!instante.equals(other.instante))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	public boolean concluidaComSucesso() {
		return this.status.equals(StatusTransacao.SUCESSO);
	}

	@Override
	public String toString() {
		return "Transacao [id=" + id + ", status=" + status + ", idTransacao=" + idTransacao + ", instante=" + instante
				+ "]";
	}
	
	
}
