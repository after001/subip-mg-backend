package com.cefet.subip_mg_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.subip_mg_backend.entities.Genero;

public interface GeneroRepository extends JpaRepository<Genero, Long> {
	boolean existsByDescricaoIgnoreCase(String descricao);
}
