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

import com.cefet.subip_mg_backend.dto.EmprestimoResponseDTO;
import com.cefet.subip_mg_backend.dto.PessoaRequestDTO;
import com.cefet.subip_mg_backend.dto.PessoaResponseDTO;
import com.cefet.subip_mg_backend.services.PessoaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	@GetMapping
	public ResponseEntity<List<PessoaResponseDTO>> listar() {
		List<PessoaResponseDTO> lista = pessoaService.listar();
		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PessoaResponseDTO> buscar(@PathVariable Long id) {
		PessoaResponseDTO dto = pessoaService.buscarPorId(id);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@GetMapping("/{id}/emprestimos")
	public ResponseEntity<List<EmprestimoResponseDTO>> listarEmprestimos(@PathVariable Long id) {
		List<EmprestimoResponseDTO> lista = pessoaService.listarEmprestimos(id);
		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}

	@PostMapping
	public ResponseEntity<PessoaResponseDTO> inserir(@Valid @RequestBody PessoaRequestDTO dto) {
		PessoaResponseDTO novo = pessoaService.inserir(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(novo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PessoaResponseDTO> alterar(@PathVariable Long id, @Valid @RequestBody PessoaRequestDTO dto) {
		PessoaResponseDTO atualizado = pessoaService.alterar(id, dto);
		return ResponseEntity.status(HttpStatus.OK).body(atualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		pessoaService.excluir(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
