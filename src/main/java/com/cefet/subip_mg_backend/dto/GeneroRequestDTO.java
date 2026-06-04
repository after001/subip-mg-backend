package com.cefet.subip_mg_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class GeneroRequestDTO {

	@NotBlank(message = "A descricao do genero e obrigatoria.")
	@Size(max = 255, message = "A descricao do genero deve possuir no maximo 255 caracteres.")
	private String descricao;

	public GeneroRequestDTO() {
	}

	public GeneroRequestDTO(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
