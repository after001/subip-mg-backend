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

import com.cefet.subip_mg_backend.dto.UsuarioRequestDTO;
import com.cefet.subip_mg_backend.dto.UsuarioResponseDTO;
import com.cefet.subip_mg_backend.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<UsuarioResponseDTO>> listar() {
		List<UsuarioResponseDTO> lista = usuarioService.listar();
		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioResponseDTO> buscar(@PathVariable Long id) {
		UsuarioResponseDTO dto = usuarioService.buscarPorId(id);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@PostMapping
	public ResponseEntity<UsuarioResponseDTO> inserir(@Valid @RequestBody UsuarioRequestDTO dto) {
		UsuarioResponseDTO novo = usuarioService.inserir(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(novo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UsuarioResponseDTO> alterar(@PathVariable Long id, @Valid @RequestBody UsuarioRequestDTO dto) {
		UsuarioResponseDTO atualizado = usuarioService.alterar(id, dto);
		return ResponseEntity.status(HttpStatus.OK).body(atualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		usuarioService.excluir(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
