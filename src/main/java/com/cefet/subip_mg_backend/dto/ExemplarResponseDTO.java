package com.cefet.subip_mg_backend.dto;

import com.cefet.subip_mg_backend.entities.Exemplar;
import com.cefet.subip_mg_backend.enums.SituacaoExemplar;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "tombo", "situacao", "livroId", "bibliotecaId" })
public class ExemplarResponseDTO {

	private Long id;
	private String tombo;
	private SituacaoExemplar situacao;
	private Long livroId;
	private Long bibliotecaId;

	public ExemplarResponseDTO() {
	}

	public ExemplarResponseDTO(Exemplar entity) {
		this.id = entity.getId();
		this.tombo = entity.getTombo();
		this.situacao = entity.getSituacao();
		this.livroId = entity.getLivro().getId();
		this.bibliotecaId = entity.getBiblioteca().getId();
	}

	public Long getId() {
		return id;
	}

	public String getTombo() {
		return tombo;
	}

	public SituacaoExemplar getSituacao() {
		return situacao;
	}

	public Long getLivroId() {
		return livroId;
	}

	public Long getBibliotecaId() {
		return bibliotecaId;
	}
}
