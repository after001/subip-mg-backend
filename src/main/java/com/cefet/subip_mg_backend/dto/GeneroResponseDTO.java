package com.cefet.subip_mg_backend.dto;

import com.cefet.subip_mg_backend.entities.Genero;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "descricao" })
public class GeneroResponseDTO {

	private Long id;
	private String descricao;

	public GeneroResponseDTO() {
	}

	public GeneroResponseDTO(Genero entity) {
		this.id = entity.getId();
		this.descricao = entity.getDescricao();
	}

	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}
}
