package com.cefet.subip_mg_backend.dto;

import com.cefet.subip_mg_backend.entities.Livro;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "titulo", "isbn" })
public class LivroResponseDTO {

	private Long id;
	private String titulo;
	private String isbn;

	public LivroResponseDTO() {
	}

	public LivroResponseDTO(Livro entity) {
		this.id = entity.getId();
		this.titulo = entity.getTitulo();
		this.isbn = entity.getIsbn();
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getIsbn() {
		return isbn;
	}
}
