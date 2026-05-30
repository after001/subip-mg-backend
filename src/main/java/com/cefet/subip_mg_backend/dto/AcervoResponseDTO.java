package com.cefet.subip_mg_backend.dto;

import com.cefet.subip_mg_backend.entities.Exemplar;
import com.cefet.subip_mg_backend.enums.SituacaoExemplar;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "livroId", "titulo", "isbn", "exemplarId", "tombo", "situacao", "bibliotecaId",
		"bibliotecaNome" })
public class AcervoResponseDTO {

	private Long livroId;
	private String titulo;
	private String isbn;
	private Long exemplarId;
	private String tombo;
	private SituacaoExemplar situacao;
	private Long bibliotecaId;
	private String bibliotecaNome;

	public AcervoResponseDTO() {
	}

	public AcervoResponseDTO(Exemplar entity) {
		this.livroId = entity.getLivro().getId();
		this.titulo = entity.getLivro().getTitulo();
		this.isbn = entity.getLivro().getIsbn();
		this.exemplarId = entity.getId();
		this.tombo = entity.getTombo();
		this.situacao = entity.getSituacao();
		this.bibliotecaId = entity.getBiblioteca().getId();
		this.bibliotecaNome = entity.getBiblioteca().getNome();
	}

	public Long getLivroId() {
		return livroId;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getIsbn() {
		return isbn;
	}

	public Long getExemplarId() {
		return exemplarId;
	}

	public String getTombo() {
		return tombo;
	}

	public SituacaoExemplar getSituacao() {
		return situacao;
	}

	public Long getBibliotecaId() {
		return bibliotecaId;
	}

	public String getBibliotecaNome() {
		return bibliotecaNome;
	}
}
