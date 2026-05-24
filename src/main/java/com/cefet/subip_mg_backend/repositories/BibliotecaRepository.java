package com.cefet.subip_mg_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.subip_mg_backend.entities.Biblioteca;

public interface BibliotecaRepository extends JpaRepository<Biblioteca, Long> {
}
