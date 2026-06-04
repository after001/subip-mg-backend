package com.cefet.subip_mg_backend.dto;

import com.cefet.subip_mg_backend.entities.Livro;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "titulo", "isbn", "generoId", "generoDescricao" })
public class LivroResponseDTO {

	private Long id;
	private String titulo;
	private String isbn;
	private Long generoId;
	private String generoDescricao;

	public LivroResponseDTO() {
	}

	public LivroResponseDTO(Livro entity) {
		this.id = entity.getId();
		this.titulo = entity.getTitulo();
		this.isbn = entity.getIsbn();
		this.generoId = entity.getGenero().getId();
		this.generoDescricao = entity.getGenero().getDescricao();
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

	public Long getGeneroId() {
		return generoId;
	}

	public String getGeneroDescricao() {
		return generoDescricao;
	}
}
