package br.com.zup.mercadolivre.controller.util;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaConverter{

	private String senha;

	public SenhaConverter(@NotBlank @Length(min = 6) String senha) {
		this.senha = senha;
	}
	
	public String passwordEncorder() {
		
		return new BCryptPasswordEncoder().encode(senha);
	}
}
