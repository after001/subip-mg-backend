package com.cefet.subip_mg_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.subip_mg_backend.entities.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

	boolean existsByPessoaId(Long pessoaId);

	boolean existsByExemplarId(Long exemplarId);

	List<Emprestimo> findByPessoaId(Long pessoaId);
}
