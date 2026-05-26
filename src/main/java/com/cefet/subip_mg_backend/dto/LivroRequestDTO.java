package com.cefet.subip_mg_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LivroRequestDTO {

	@NotBlank(message = "O titulo e obrigatorio.")
	@Size(min = 2, max = 200, message = "O titulo deve ter entre 2 e 200 caracteres.")
	private String titulo;

	@NotBlank(message = "O ISBN e obrigatorio.")
	@Size(min = 10, max = 17, message = "O ISBN deve ter entre 10 e 17 caracteres.")
	private String isbn;

	public LivroRequestDTO() {
	}

	public LivroRequestDTO(String titulo, String isbn) {
		this.titulo = titulo;
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
}
