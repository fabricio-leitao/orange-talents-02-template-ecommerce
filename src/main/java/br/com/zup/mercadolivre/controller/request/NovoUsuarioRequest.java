package br.com.zup.mercadolivre.controller.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.zup.mercadolivre.controller.util.SenhaConverter;
import br.com.zup.mercadolivre.model.Usuario;
import br.com.zup.mercadolivre.validator.UniqueValue;

public class NovoUsuarioRequest {

	@NotBlank
	@Email
	@UniqueValue(domainClass = Usuario.class, fieldName = "email")
	private String email;
	@NotBlank @Length(min = 6)
	private String senha;
	
	public NovoUsuarioRequest(@NotBlank @Email String email, @NotBlank @Length(min = 6) String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}

	public Usuario toModel() {
		return new Usuario(email, new SenhaConverter(senha));
	}	
	
}
