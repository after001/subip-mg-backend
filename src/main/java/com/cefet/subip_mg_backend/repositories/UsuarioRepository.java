package com.cefet.subip_mg_backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.subip_mg_backend.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	boolean existsByLogin(String login);
	boolean existsByPessoaId(Long pessoaId);
	Optional<Usuario> findByLogin(String login);
}
