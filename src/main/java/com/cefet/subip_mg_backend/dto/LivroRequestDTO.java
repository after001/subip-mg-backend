package com.cefet.subip_mg_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class LivroRequestDTO {

	@NotBlank(message = "O titulo e obrigatorio.")
	@Size(max = 255, message = "O titulo deve possuir no maximo 255 caracteres.")
	private String titulo;

	@NotBlank(message = "O ISBN e obrigatorio.")
	@Pattern(
			regexp = "^(\\d{13}|\\d{3}\\.\\d{2}\\.\\d{3}-\\d{4}-\\d)$",
			message = "O ISBN deve possuir 13 digitos numericos ou seguir o formato 978.85.359-0277-7."
	)
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
