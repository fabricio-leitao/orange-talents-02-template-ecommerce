package br.com.zup.mercadolivre.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.zup.mercadolivre.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long>{

	Optional<Usuario> findByEmail(String email);

	Usuario findByEmail(Long idUsuario);

}
