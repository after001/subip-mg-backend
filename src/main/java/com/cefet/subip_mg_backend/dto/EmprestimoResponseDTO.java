package com.cefet.subip_mg_backend.dto;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.cefet.subip_mg_backend.entities.Emprestimo;
import com.cefet.subip_mg_backend.enums.SituacaoEmprestimo;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "situacao", "dataRetirada", "dataDevolucaoPrevista", "dataDevolucao", "exemplarId",
		"pessoaId", "diasAtraso" })
public class EmprestimoResponseDTO {

	private Long id;
	private SituacaoEmprestimo situacao;
	private LocalDate dataRetirada;
	private LocalDate dataDevolucaoPrevista;
	private LocalDate dataDevolucao;
	private Long exemplarId;
	private Long pessoaId;
	private Long diasAtraso;

	public EmprestimoResponseDTO() {
	}

	public EmprestimoResponseDTO(Emprestimo entity) {
		this.id = entity.getId();
		this.situacao = entity.getSituacao();
		this.dataRetirada = entity.getDataRetirada();
		this.dataDevolucaoPrevista = entity.getDataDevolucaoPrevista();
		this.dataDevolucao = entity.getDataDevolucao();
		this.exemplarId = entity.getExemplar().getId();
		this.pessoaId = entity.getPessoa().getId();
		this.diasAtraso = calcularDiasAtraso(entity);
	}

	public Long getId() {
		return id;
	}

	public SituacaoEmprestimo getSituacao() {
		return situacao;
	}

	public LocalDate getDataRetirada() {
		return dataRetirada;
	}

	public LocalDate getDataDevolucaoPrevista() {
		return dataDevolucaoPrevista;
	}

	public LocalDate getDataDevolucao() {
		return dataDevolucao;
	}

	public Long getExemplarId() {
		return exemplarId;
	}

	public Long getPessoaId() {
		return pessoaId;
	}

	public Long getDiasAtraso() {
		return diasAtraso;
	}

	private Long calcularDiasAtraso(Emprestimo entity) {
		if (entity.getDataDevolucaoPrevista() == null) {
			return 0L;
		}

		LocalDate dataReferencia = entity.getDataDevolucao() != null ? entity.getDataDevolucao() : LocalDate.now();
		long dias = ChronoUnit.DAYS.between(entity.getDataDevolucaoPrevista(), dataReferencia);

		return Math.max(dias, 0L);
	}
}
