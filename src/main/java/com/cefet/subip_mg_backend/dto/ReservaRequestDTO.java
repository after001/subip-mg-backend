package com.cefet.subip_mg_backend.dto;

import jakarta.validation.constraints.NotNull;

public class ReservaRequestDTO {

	@NotNull(message = "O ID da pessoa e obrigatorio para registrar a reserva.")
	private Long pessoaId;

	@NotNull(message = "O ID do exemplar e obrigatorio para registrar a reserva.")
	private Long exemplarId;

	public ReservaRequestDTO() {
	}

	public ReservaRequestDTO(Long pessoaId, Long exemplarId) {
		this.pessoaId = pessoaId;
		this.exemplarId = exemplarId;
	}

	public Long getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(Long pessoaId) {
		this.pessoaId = pessoaId;
	}

	public Long getExemplarId() {
		return exemplarId;
	}

	public void setExemplarId(Long exemplarId) {
		this.exemplarId = exemplarId;
	}
}
