package com.cefet.subip_mg_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PessoaRequestDTO {

	@NotBlank(message = "O nome e obrigatorio.")
	@Size(max = 255, message = "O nome deve possuir no maximo 255 caracteres.")
	private String nome;

	@NotBlank(message = "O CPF e obrigatorio.")
	@Size(min = 11, max = 14, message = "O CPF deve ter entre 11 e 14 caracteres.")
	private String cpf;

	@NotBlank(message = "O email e obrigatorio.")
	@Email(message = "O email deve ser valido.")
	@Size(min = 6, max = 254, message = "O email deve ter entre 6 e 254 caracteres.")
	private String email;

	public PessoaRequestDTO() {
	}

	public PessoaRequestDTO(String nome, String cpf, String email) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
