package com.cefet.subip_mg_backend.entities;

import java.time.LocalDate;

import com.cefet.subip_mg_backend.enums.SituacaoEmprestimo;

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
@Table(name = "tb_emprestimo")
public class Emprestimo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private SituacaoEmprestimo situacao;

	@Column(nullable = false)
	private LocalDate dataRetirada;

	@Column(nullable = false)
	private LocalDate dataDevolucaoPrevista;

	private LocalDate dataDevolucao;

	@ManyToOne
	@JoinColumn(name = "exemplar_id", nullable = false)
	private Exemplar exemplar;

	@ManyToOne
	@JoinColumn(name = "pessoa_id", nullable = false)
	private Pessoa pessoa;

	public Emprestimo() {
	}

	public Emprestimo(Long id, SituacaoEmprestimo situacao, LocalDate dataRetirada,
			LocalDate dataDevolucaoPrevista, LocalDate dataDevolucao, Exemplar exemplar, Pessoa pessoa) {
		this.id = id;
		this.situacao = situacao;
		this.dataRetirada = dataRetirada;
		this.dataDevolucaoPrevista = dataDevolucaoPrevista;
		this.dataDevolucao = dataDevolucao;
		this.exemplar = exemplar;
		this.pessoa = pessoa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SituacaoEmprestimo getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoEmprestimo situacao) {
		this.situacao = situacao;
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

	public LocalDate getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public Exemplar getExemplar() {
		return exemplar;
	}

	public void setExemplar(Exemplar exemplar) {
		this.exemplar = exemplar;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}
