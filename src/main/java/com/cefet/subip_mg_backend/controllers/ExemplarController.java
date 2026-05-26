package com.cefet.subip_mg_backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cefet.subip_mg_backend.dto.ExemplarRequestDTO;
import com.cefet.subip_mg_backend.dto.ExemplarResponseDTO;
import com.cefet.subip_mg_backend.services.ExemplarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/exemplares")
public class ExemplarController {

	@Autowired
	private ExemplarService exemplarService;

	@GetMapping
	public ResponseEntity<List<ExemplarResponseDTO>> listar() {
		List<ExemplarResponseDTO> lista = exemplarService.listar();
		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ExemplarResponseDTO> buscar(@PathVariable Long id) {
		ExemplarResponseDTO dto = exemplarService.buscarPorId(id);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@PostMapping
	public ResponseEntity<ExemplarResponseDTO> inserir(@Valid @RequestBody ExemplarRequestDTO dto) {
		ExemplarResponseDTO novo = exemplarService.inserir(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(novo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ExemplarResponseDTO> alterar(@PathVariable Long id, @Valid @RequestBody ExemplarRequestDTO dto) {
		ExemplarResponseDTO atualizado = exemplarService.alterar(id, dto);
		return ResponseEntity.status(HttpStatus.OK).body(atualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		exemplarService.excluir(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
