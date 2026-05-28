package com.cefet.subip_mg_backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cefet.subip_mg_backend.dto.EmprestimoRequestDTO;
import com.cefet.subip_mg_backend.dto.EmprestimoResponseDTO;
import com.cefet.subip_mg_backend.services.EmprestimoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

	@Autowired
	private EmprestimoService emprestimoService;

	@GetMapping
	public ResponseEntity<List<EmprestimoResponseDTO>> listar() {
		List<EmprestimoResponseDTO> lista = emprestimoService.listar();
		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmprestimoResponseDTO> buscar(@PathVariable Long id) {
		EmprestimoResponseDTO dto = emprestimoService.buscarPorId(id);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@PostMapping
	public ResponseEntity<EmprestimoResponseDTO> registrar(@Valid @RequestBody EmprestimoRequestDTO dto) {
		EmprestimoResponseDTO novo = emprestimoService.registrar(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(novo);
	}
}
