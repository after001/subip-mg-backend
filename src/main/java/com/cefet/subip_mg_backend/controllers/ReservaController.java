package com.cefet.subip_mg_backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cefet.subip_mg_backend.dto.ReservaRequestDTO;
import com.cefet.subip_mg_backend.dto.ReservaResponseDTO;
import com.cefet.subip_mg_backend.services.ReservaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

	@Autowired
	private ReservaService reservaService;

	@GetMapping
	public ResponseEntity<List<ReservaResponseDTO>> listar() {
		List<ReservaResponseDTO> lista = reservaService.listar();
		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ReservaResponseDTO> buscar(@PathVariable Long id) {
		ReservaResponseDTO dto = reservaService.buscarPorId(id);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@PostMapping
	public ResponseEntity<ReservaResponseDTO> registrar(@Valid @RequestBody ReservaRequestDTO dto) {
		ReservaResponseDTO novo = reservaService.registrar(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(novo);
	}

	@PutMapping("/{id}/cancelamento")
	public ResponseEntity<ReservaResponseDTO> cancelar(@PathVariable Long id) {
		ReservaResponseDTO atualizado = reservaService.cancelar(id);
		return ResponseEntity.status(HttpStatus.OK).body(atualizado);
	}
}
