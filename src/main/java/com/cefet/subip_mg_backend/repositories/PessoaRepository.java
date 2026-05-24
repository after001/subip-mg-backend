package com.cefet.subip_mg_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.subip_mg_backend.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	boolean existsByCpf(String cpf);
	boolean existsByEmail(String email);
}
