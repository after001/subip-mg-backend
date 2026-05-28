package com.cefet.subip_mg_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BibliotecaRequestDTO {

	@NotBlank(message = "O nome e obrigatorio.")
	@Size(max = 255, message = "O nome deve possuir no maximo 255 caracteres.")
	private String nome;

	public BibliotecaRequestDTO() {
	}

	public BibliotecaRequestDTO(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
