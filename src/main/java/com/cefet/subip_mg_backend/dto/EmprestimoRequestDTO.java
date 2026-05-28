package com.cefet.subip_mg_backend.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public class EmprestimoRequestDTO {

	@NotNull(message = "A data de retirada e obrigatoria para registrar o emprestimo.")
	private LocalDate dataRetirada;

	@NotNull(message = "A data de devolucao prevista e obrigatoria para registrar o emprestimo.")
	private LocalDate dataDevolucaoPrevista;

	@NotNull(message = "O ID do exemplar e obrigatorio para registrar o emprestimo.")
	private Long exemplarId;

	@NotNull(message = "O ID da pessoa e obrigatorio para registrar o emprestimo.")
	private Long pessoaId;

	public EmprestimoRequestDTO() {
	}

	public EmprestimoRequestDTO(LocalDate dataRetirada, LocalDate dataDevolucaoPrevista, Long exemplarId,
			Long pessoaId) {
		this.dataRetirada = dataRetirada;
		this.dataDevolucaoPrevista = dataDevolucaoPrevista;
		this.exemplarId = exemplarId;
		this.pessoaId = pessoaId;
	}

	public LocalDate getDataRetirada() {
		return dataRetirada;
	}

	public void setDataRetirada(LocalDate dataRetirada) {
		this.dataRetirada = dataRetirada;
	}

	public LocalDate getDataDevolucaoPrevista() {
		return dataDevolucaoPrevista;
	}

	public void setDataDevolucaoPrevista(LocalDate dataDevolucaoPrevista) {
		this.dataDevolucaoPrevista = dataDevolucaoPrevista;
	}

	public Long getExemplarId() {
		return exemplarId;
	}

	public void setExemplarId(Long exemplarId) {
		this.exemplarId = exemplarId;
	}

	public Long getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(Long pessoaId) {
		this.pessoaId = pessoaId;
	}
}
