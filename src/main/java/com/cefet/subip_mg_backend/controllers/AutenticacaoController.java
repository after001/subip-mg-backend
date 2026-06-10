package com.cefet.subip_mg_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cefet.subip_mg_backend.dto.AutenticacaoRequestDTO;
import com.cefet.subip_mg_backend.dto.UsuarioResponseDTO;
import com.cefet.subip_mg_backend.services.AutenticacaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {

	@Autowired
	private AutenticacaoService autenticacaoService;

	@PostMapping("/login")
	public ResponseEntity<UsuarioResponseDTO> autenticar(@Valid @RequestBody AutenticacaoRequestDTO dto) {
		UsuarioResponseDTO usuario = autenticacaoService.autenticar(dto);
		return ResponseEntity.status(HttpStatus.OK).body(usuario);
	}
}
