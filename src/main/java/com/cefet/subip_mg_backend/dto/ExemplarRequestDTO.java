package com.cefet.subip_mg_backend.dto;

import com.cefet.subip_mg_backend.enums.SituacaoExemplar;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ExemplarRequestDTO {

	@NotBlank(message = "O tombo e obrigatorio.")
	@Size(max = 50, message = "O tombo deve possuir no maximo 50 caracteres.")
	private String tombo;

	@NotNull(message = "A situacao e obrigatoria.")
	private SituacaoExemplar situacao;

	@NotNull(message = "O livroId e obrigatorio.")
	private Long livroId;

	@NotNull(message = "O bibliotecaId e obrigatorio.")
	private Long bibliotecaId;

	public ExemplarRequestDTO() {
	}

	public ExemplarRequestDTO(String tombo, SituacaoExemplar situacao, Long livroId, Long bibliotecaId) {
		this.tombo = tombo;
		this.situacao = situacao;
		this.livroId = livroId;
		this.bibliotecaId = bibliotecaId;
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

	public Long getLivroId() {
		return livroId;
	}

	public void setLivroId(Long livroId) {
		this.livroId = livroId;
	}

	public Long getBibliotecaId() {
		return bibliotecaId;
	}

	public void setBibliotecaId(Long bibliotecaId) {
		this.bibliotecaId = bibliotecaId;
	}
}
