package br.com.zup.mercadolivre.component;

import org.springframework.stereotype.Component;

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

	
}
