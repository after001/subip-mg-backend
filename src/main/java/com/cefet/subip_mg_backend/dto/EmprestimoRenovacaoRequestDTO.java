package com.cefet.subip_mg_backend.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public class EmprestimoRenovacaoRequestDTO {

	@NotNull(message = "A nova data de devolucao prevista e obrigatoria para renovar o emprestimo.")
	private LocalDate dataDevolucaoPrevista;

	public EmprestimoRenovacaoRequestDTO() {
	}

	public EmprestimoRenovacaoRequestDTO(LocalDate dataDevolucaoPrevista) {
		this.dataDevolucaoPrevista = dataDevolucaoPrevista;
	}

	public LocalDate getDataDevolucaoPrevista() {
		return dataDevolucaoPrevista;
	}

	public void setDataDevolucaoPrevista(LocalDate dataDevolucaoPrevista) {
		this.dataDevolucaoPrevista = dataDevolucaoPrevista;
	}
}
