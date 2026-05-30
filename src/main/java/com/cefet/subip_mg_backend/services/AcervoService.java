package com.cefet.subip_mg_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cefet.subip_mg_backend.dto.AcervoResponseDTO;
import com.cefet.subip_mg_backend.entities.Exemplar;
import com.cefet.subip_mg_backend.enums.SituacaoExemplar;
import com.cefet.subip_mg_backend.repositories.ExemplarRepository;

@Service
public class AcervoService {

	@Autowired
	private ExemplarRepository exemplarRepository;

	// O acervo nasce do exemplar porque disponibilidade e localizacao pertencem a ele.
	@Transactional(readOnly = true)
	public List<AcervoResponseDTO> listar(String titulo, Long bibliotecaId, SituacaoExemplar situacao) {
		List<Exemplar> lista = exemplarRepository.buscarAcervo(normalizarTexto(titulo), bibliotecaId, situacao);
		return lista.stream().map(AcervoResponseDTO::new).toList();
	}

	private String normalizarTexto(String valor) {
		if (valor == null || valor.isBlank()) {
			return null;
		}

		return valor.trim();
	}
}
