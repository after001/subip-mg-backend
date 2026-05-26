package com.cefet.subip_mg_backend.dto;

import com.cefet.subip_mg_backend.entities.Pessoa;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "nome", "cpf", "email" })
public class PessoaResponseDTO {

	private Long id;
	private String nome;
	private String cpf;
	private String email;

	public PessoaResponseDTO() {
	}

	public PessoaResponseDTO(Pessoa entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.cpf = entity.getCpf();
		this.email = entity.getEmail();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getEmail() {
		return email;
	}
}
