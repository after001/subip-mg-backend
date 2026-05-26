package com.cefet.subip_mg_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.subip_mg_backend.entities.Exemplar;

public interface ExemplarRepository extends JpaRepository<Exemplar, Long> {
	boolean existsByTombo(String tombo);
	boolean existsByLivroId(Long livroId);
	boolean existsByBibliotecaId(Long bibliotecaId);
	List<Exemplar> findByLivroId(Long livroId);
	List<Exemplar> findByBibliotecaId(Long bibliotecaId);
}
