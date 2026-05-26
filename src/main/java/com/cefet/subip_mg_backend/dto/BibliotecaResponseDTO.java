package com.cefet.subip_mg_backend.dto;

import com.cefet.subip_mg_backend.entities.Biblioteca;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "nome" })
public class BibliotecaResponseDTO {

	private Long id;
	private String nome;

	public BibliotecaResponseDTO() {
	}

	public BibliotecaResponseDTO(Biblioteca entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
}
