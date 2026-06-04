package com.cefet.subip_mg_backend.dto;

import java.time.LocalDate;

import com.cefet.subip_mg_backend.entities.Reserva;
import com.cefet.subip_mg_backend.enums.SituacaoReserva;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "situacao", "dataRegistro", "exemplarId", "pessoaId" })
public class ReservaResponseDTO {

	private Long id;
	private SituacaoReserva situacao;
	private LocalDate dataRegistro;
	private Long exemplarId;
	private Long pessoaId;

	public ReservaResponseDTO() {
	}

	public ReservaResponseDTO(Reserva entity) {
		this.id = entity.getId();
		this.situacao = entity.getSituacao();
		this.dataRegistro = entity.getDataRegistro();
		this.exemplarId = entity.getExemplar().getId();
		this.pessoaId = entity.getPessoa().getId();
	}

	public Long getId() {
		return id;
	}

	public SituacaoReserva getSituacao() {
		return situacao;
	}

	public LocalDate getDataRegistro() {
		return dataRegistro;
	}

	public Long getExemplarId() {
		return exemplarId;
	}

	public Long getPessoaId() {
		return pessoaId;
	}
}
