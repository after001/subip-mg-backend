package com.cefet.subip_mg_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.subip_mg_backend.entities.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
	boolean existsByIsbn(String isbn);
}
