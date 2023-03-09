package br.com.springboot.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.springboot.backend.model.Usuario;

public interface UsuarioRepository extends JpaRepository<br.com.springboot.backend.model.Usuario, Long>{
	
	@Query(value= "select u from Usuario u where u.nome like %?1%")
	List<Usuario> buscarPorNome(String nome);
	
}
