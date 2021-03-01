package br.com.zup.mercadolivre.component;

import org.springframework.stereotype.Component;

import br.com.zup.mercadolivre.fechamentoCompra.Compra;
import br.com.zup.mercadolivre.model.Pergunta;

@Component
public class Email {

	public void enviarEmail(Pergunta pergunta) {

		System.out.println("-----------------------------------");
		System.out.println("Uma nova pergunta foi feita referente ao produto: " + pergunta.getProduto().getNome());
		System.out.println("Usuário: " + pergunta.getUsuario().getEmail());
		System.out.println("Título: " + pergunta.getTitulo());
		System.out.println("-----------------------------------");
	}

	public void confirmarCompra(Compra compra) {
		System.out.println("-----------------------------------");
		System.out.println("Uma compra foi realizada com sucesso!");
		System.out.println("Produto: " + compra.getProduto().getNome());
		System.out.println("Quantidade: " + compra.getQuantidade());
		System.out.println("Comprador: " + compra.getComprador().getEmail());
		System.out.println("Forma de Pagamento: " + compra.getPagamento());
		System.out.println("-----------------------------------");
		
	}

	
}
