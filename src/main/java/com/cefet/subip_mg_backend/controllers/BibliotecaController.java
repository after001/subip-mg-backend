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

import com.cefet.subip_mg_backend.dto.BibliotecaRequestDTO;
import com.cefet.subip_mg_backend.dto.BibliotecaResponseDTO;
import com.cefet.subip_mg_backend.services.BibliotecaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bibliotecas")
public class BibliotecaController {

	@Autowired
	private BibliotecaService bibliotecaService;

	@GetMapping
	public ResponseEntity<List<BibliotecaResponseDTO>> listar() {
		List<BibliotecaResponseDTO> lista = bibliotecaService.listar();
		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}

	@GetMapping("/{id}")
	public ResponseEntity<BibliotecaResponseDTO> buscar(@PathVariable Long id) {
		BibliotecaResponseDTO dto = bibliotecaService.buscarPorId(id);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@PostMapping
	public ResponseEntity<BibliotecaResponseDTO> inserir(@Valid @RequestBody BibliotecaRequestDTO dto) {
		BibliotecaResponseDTO novo = bibliotecaService.inserir(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(novo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<BibliotecaResponseDTO> alterar(@PathVariable Long id, @Valid @RequestBody BibliotecaRequestDTO dto) {
		BibliotecaResponseDTO atualizado = bibliotecaService.alterar(id, dto);
		return ResponseEntity.status(HttpStatus.OK).body(atualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		bibliotecaService.excluir(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
