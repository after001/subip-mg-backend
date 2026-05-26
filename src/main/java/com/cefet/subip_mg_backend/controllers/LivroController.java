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

import com.cefet.subip_mg_backend.dto.LivroRequestDTO;
import com.cefet.subip_mg_backend.dto.LivroResponseDTO;
import com.cefet.subip_mg_backend.services.LivroService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/livros")
public class LivroController {

	@Autowired
	private LivroService livroService;

	@GetMapping
	public ResponseEntity<List<LivroResponseDTO>> listar() {
		List<LivroResponseDTO> lista = livroService.listar();
		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}

	@GetMapping("/{id}")
	public ResponseEntity<LivroResponseDTO> buscar(@PathVariable Long id) {
		LivroResponseDTO dto = livroService.buscarPorId(id);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@PostMapping
	public ResponseEntity<LivroResponseDTO> inserir(@Valid @RequestBody LivroRequestDTO dto) {
		LivroResponseDTO novo = livroService.inserir(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(novo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<LivroResponseDTO> alterar(@PathVariable Long id, @Valid @RequestBody LivroRequestDTO dto) {
		LivroResponseDTO atualizado = livroService.alterar(id, dto);
		return ResponseEntity.status(HttpStatus.OK).body(atualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		livroService.excluir(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
