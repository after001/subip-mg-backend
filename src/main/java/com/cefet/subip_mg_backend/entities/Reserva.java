package com.cefet.subip_mg_backend.entities;

import java.time.LocalDate;

import com.cefet.subip_mg_backend.enums.SituacaoReserva;

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
@Table(name = "tb_reserva")
public class Reserva {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private SituacaoReserva situacao;

	@Column(nullable = false)
	private LocalDate dataRegistro;

	@ManyToOne
	@JoinColumn(name = "exemplar_id", nullable = false)
	private Exemplar exemplar;

	@ManyToOne
	@JoinColumn(name = "pessoa_id", nullable = false)
	private Pessoa pessoa;

	public Reserva() {
	}

	public Reserva(Long id, SituacaoReserva situacao, LocalDate dataRegistro, Exemplar exemplar, Pessoa pessoa) {
		this.id = id;
		this.situacao = situacao;
		this.dataRegistro = dataRegistro;
		this.exemplar = exemplar;
		this.pessoa = pessoa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SituacaoReserva getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoReserva situacao) {
		this.situacao = situacao;
	}

	public LocalDate getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(LocalDate dataRegistro) {
		this.dataRegistro = dataRegistro;
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
