package com.cefet.subip_mg_backend.entities;

import com.cefet.subip_mg_backend.enums.SituacaoExemplar;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_exemplar")
public class Exemplar {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true, length = 50)
	private String tombo;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private SituacaoExemplar situacao;

	@ManyToOne
	@JoinColumn(name = "livro_id", nullable = false)
	private Livro livro;

	@ManyToOne
	@JoinColumn(name = "biblioteca_id", nullable = false)
	private Biblioteca biblioteca;

	public Exemplar() {
	}

	public Exemplar(Long id, String tombo, SituacaoExemplar situacao, Livro livro, Biblioteca biblioteca) {
		this.id = id;
		this.tombo = tombo;
		this.situacao = situacao;
		this.livro = livro;
		this.biblioteca = biblioteca;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTombo() {
		return tombo;
	}

	public void setTombo(String tombo) {
		this.tombo = tombo;
	}

	public SituacaoExemplar getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoExemplar situacao) {
		this.situacao = situacao;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Biblioteca getBiblioteca() {
		return biblioteca;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}
}
