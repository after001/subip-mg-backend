package com.cefet.subip_mg_backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cefet.subip_mg_backend.dto.AcervoResponseDTO;
import com.cefet.subip_mg_backend.enums.SituacaoExemplar;
import com.cefet.subip_mg_backend.services.AcervoService;

@RestController
@RequestMapping("/acervo")
public class AcervoController {

	@Autowired
	private AcervoService acervoService;

	@GetMapping
	public ResponseEntity<List<AcervoResponseDTO>> listar(
			@RequestParam(required = false) String titulo,
			@RequestParam(required = false) Long bibliotecaId,
			@RequestParam(required = false) SituacaoExemplar situacao) {
		List<AcervoResponseDTO> lista = acervoService.listar(titulo, bibliotecaId, situacao);
		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}
}
