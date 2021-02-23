package br.com.zup.mercadolivre.controller.util;

import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.zup.mercadolivre.model.Opiniao;

@Component
public class Opinioes {

	private Set<Opiniao> opinioes;

	public Opinioes(Set<Opiniao> opinioes) {
		this.opinioes = opinioes;
	}
	public <T> Set<T> mapeiaOpinioes(Function<Opiniao, T> funcaoMap) {
		return this.opinioes.stream().map(funcaoMap).collect(Collectors.toSet());
	}
	public double media() {
		Set<Integer> notas = mapeiaOpinioes(opiniao -> opiniao.getNota());
		OptionalDouble media = notas.stream().mapToInt(nota -> nota).average();
		return media.orElse(0.0);
	}
	
	public int total() {
		return this.opinioes.size();
	}
}
