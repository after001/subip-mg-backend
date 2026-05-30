package com.cefet.subip_mg_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cefet.subip_mg_backend.entities.Exemplar;
import com.cefet.subip_mg_backend.enums.SituacaoExemplar;

public interface ExemplarRepository extends JpaRepository<Exemplar, Long> {
	boolean existsByTombo(String tombo);
	boolean existsByLivroId(Long livroId);
	boolean existsByBibliotecaId(Long bibliotecaId);
	List<Exemplar> findByLivroId(Long livroId);
	List<Exemplar> findByBibliotecaId(Long bibliotecaId);

	// Traz livro e biblioteca juntos para entregar uma visao pronta ao frontend.
	@Query("""
			SELECT e
			FROM Exemplar e
			JOIN FETCH e.livro l
			JOIN FETCH e.biblioteca b
			WHERE (:titulo IS NULL OR LOWER(l.titulo) LIKE LOWER(CONCAT('%', :titulo, '%')))
			AND (:bibliotecaId IS NULL OR b.id = :bibliotecaId)
			AND (:situacao IS NULL OR e.situacao = :situacao)
			""")
	List<Exemplar> buscarAcervo(
			@Param("titulo") String titulo,
			@Param("bibliotecaId") Long bibliotecaId,
			@Param("situacao") SituacaoExemplar situacao);
}
