package com.cefet.subip_mg_backend.dto;

import jakarta.validation.constraints.NotBlank;

public class AutenticacaoRequestDTO {
	@NotBlank(message = "O login e obrigatorio.")
	private String login;

	@NotBlank(message = "A senha e obrigatoria.")
	private String senha;

	public AutenticacaoRequestDTO() {
	}

	public AutenticacaoRequestDTO(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
