package com.cefet.subip_mg_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class LivroRequestDTO {

	@NotBlank(message = "O titulo e obrigatorio.")
	@Size(max = 255, message = "O titulo deve ter entre 2 e 254 caracteres.")
	private String titulo;

	@NotBlank(message = "O ISBN é obrigatório.")
	@Pattern(
			regexp = "^(\\d{13}|(978|979)-\\d{1,5}-\\d{1,7}-\\d{1,7}-\\d{1})$",
			message = "O ISBN deve ter 13 dígitos numéricos ou o formato padrão com hífens (ex: 978-85-359-0277-7)."
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
