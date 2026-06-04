package com.cefet.subip_mg_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cefet.subip_mg_backend.entities.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
	boolean existsByIsbn(String isbn);
	boolean existsByGeneroId(Long generoId);

	// Mantem a listagem e a busca de catalogo no mesmo endpoint.
	@Query("""
			SELECT l
			FROM Livro l
			WHERE (:titulo IS NULL OR LOWER(l.titulo) LIKE LOWER(CONCAT('%', :titulo, '%')))
			AND (:isbn IS NULL OR l.isbn = :isbn)
			""")
	List<Livro> buscarPorFiltros(@Param("titulo") String titulo, @Param("isbn") String isbn);
}
