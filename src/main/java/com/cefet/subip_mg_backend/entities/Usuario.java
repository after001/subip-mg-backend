package com.cefet.subip_mg_backend.entities;

import com.cefet.subip_mg_backend.enums.PerfilUsuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true, length = 100)
	private String login;

	@Column(nullable = false, length = 255)
	private String senha;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private PerfilUsuario perfil;

	@OneToOne
	@JoinColumn(name = "pessoa_id", nullable = false, unique = true)
	private Pessoa pessoa;

	public Usuario() {
	}

	public Usuario(Long id, String login, String senha, PerfilUsuario perfil, Pessoa pessoa) {
		this.id = id;
		this.login = login;
		this.senha = senha;
		this.perfil = perfil;
		this.pessoa = pessoa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public PerfilUsuario getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilUsuario perfil) {
		this.perfil = perfil;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}
