package com.cefet.subip_mg_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BibliotecaRequestDTO {

	@NotBlank(message = "O nome e obrigatorio.")
	@Size(min = 3, max = 150, message = "O nome deve ter entre 3 e 150 caracteres.")
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
