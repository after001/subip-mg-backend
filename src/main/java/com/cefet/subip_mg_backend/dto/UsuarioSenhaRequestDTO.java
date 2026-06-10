package com.cefet.subip_mg_backend.dto;

import jakarta.validation.constraints.NotBlank;

public class UsuarioSenhaRequestDTO {
	@NotBlank(message = "A senha atual e obrigatoria.")
	private String senhaAtual;

	@NotBlank(message = "A nova senha e obrigatoria.")
	private String novaSenha;

	public UsuarioSenhaRequestDTO() {
	}

	public UsuarioSenhaRequestDTO(String senhaAtual, String novaSenha) {
		this.senhaAtual = senhaAtual;
		this.novaSenha = novaSenha;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}
}
