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

import com.cefet.subip_mg_backend.dto.GeneroRequestDTO;
import com.cefet.subip_mg_backend.dto.GeneroResponseDTO;
import com.cefet.subip_mg_backend.services.GeneroService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/generos")
public class GeneroController {

	@Autowired
	private GeneroService generoService;

	@GetMapping
	public ResponseEntity<List<GeneroResponseDTO>> listar() {
		List<GeneroResponseDTO> lista = generoService.listar();
		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}

	@GetMapping("/{id}")
	public ResponseEntity<GeneroResponseDTO> buscar(@PathVariable Long id) {
		GeneroResponseDTO dto = generoService.buscarPorId(id);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@PostMapping
	public ResponseEntity<GeneroResponseDTO> inserir(@Valid @RequestBody GeneroRequestDTO dto) {
		GeneroResponseDTO novo = generoService.inserir(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(novo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<GeneroResponseDTO> alterar(@PathVariable Long id, @Valid @RequestBody GeneroRequestDTO dto) {
		GeneroResponseDTO atualizado = generoService.alterar(id, dto);
		return ResponseEntity.status(HttpStatus.OK).body(atualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		generoService.excluir(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
