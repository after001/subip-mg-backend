package com.cefet.subip_mg_backend.dto;

import com.cefet.subip_mg_backend.entities.Usuario;
import com.cefet.subip_mg_backend.enums.PerfilUsuario;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "login", "perfil", "pessoaId", "pessoaNome" })
public class UsuarioResponseDTO {

	private Long id;
	private String login;
	private PerfilUsuario perfil;
	private Long pessoaId;
	private String pessoaNome;

	public UsuarioResponseDTO() {
	}

	public UsuarioResponseDTO(Usuario entity) {
		this.id = entity.getId();
		this.login = entity.getLogin();
		this.perfil = entity.getPerfil();
		this.pessoaId = entity.getPessoa().getId();
		this.pessoaNome = entity.getPessoa().getNome();
	}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public PerfilUsuario getPerfil() {
		return perfil;
	}

	public Long getPessoaId() {
		return pessoaId;
	}

	public String getPessoaNome() {
		return pessoaNome;
	}
}
