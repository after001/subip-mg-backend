package com.cefet.subip_mg_backend.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public class EmprestimoDevolucaoRequestDTO {

	@NotNull(message = "A data de devolucao e obrigatoria para registrar a devolucao.")
	private LocalDate dataDevolucao;

	public EmprestimoDevolucaoRequestDTO() {
	}

	public EmprestimoDevolucaoRequestDTO(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public LocalDate getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
}
