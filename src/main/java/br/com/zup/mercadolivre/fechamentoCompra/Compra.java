package br.com.zup.mercadolivre.fechamentoCompra;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;

@Entity
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotNull @Positive int quantidade;
	@Enumerated
	private @NotNull PagamentoEscolhido pagamento;
	@Enumerated
	private StatusCompra status = StatusCompra.INICIADA;
	@NotNull
	@Valid
	@ManyToOne
	private Produto produto;
	@NotNull
	@Valid
	@ManyToOne
	private Usuario comprador;
	@OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
	private Set<Transacao> transacoes = new HashSet<>();

	@Deprecated
	public Compra() {

	}

	public Compra(@NotNull @Positive int quantidade, @NotNull PagamentoEscolhido pagamento, Produto produto,
			Usuario comprador) {
		this.quantidade = quantidade;
		this.pagamento = pagamento;
		this.produto = produto;
		this.comprador = comprador;
	}

	public long getId() {
		return id;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public PagamentoEscolhido getPagamento() {
		return pagamento;
	}

	public StatusCompra getStatus() {
		return status;
	}

	public Produto getProduto() {
		return produto;
	}

	public Usuario getComprador() {
		return comprador;
	}

	@Override
	public String toString() {
		return "Compra [id=" + id + ", quantidade=" + quantidade + ", pagamento=" + pagamento + ", status=" + status
				+ ", produto=" + produto + ", comprador=" + comprador + ", transacoes=" + transacoes + "]";
	}

	public void adicionaTransacao(@Valid RetornoPagamento request) {
		Transacao novaTransacao = request.toTransacao(this);
		Assert.isTrue(!this.transacoes.contains(novaTransacao), "Já existe uma transação igual");

		Assert.isTrue(transacoesConcluidasComSucesso().isEmpty(), "Essa compra já foi concluída com sucesso");

		this.transacoes.add(request.toTransacao(this));
	}

	private Set<Transacao> transacoesConcluidasComSucesso() {
		Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream().filter(Transacao::concluidaComSucesso)
				.collect(Collectors.toSet());

		Assert.isTrue(transacoesConcluidasComSucesso.size() <= 1, "Transações demais");
		return transacoesConcluidasComSucesso;
	}

	public boolean processadaComSucesso() {
		return !transacoesConcluidasComSucesso().isEmpty();
	}

	public String urlRedirecionamento(UriComponentsBuilder uri) {
		return this.pagamento.criaUrlRetorno(this, uri);
	}
	
}
