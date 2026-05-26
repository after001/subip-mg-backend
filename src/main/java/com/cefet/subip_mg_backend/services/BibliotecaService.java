package com.cefet.subip_mg_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cefet.subip_mg_backend.dto.BibliotecaRequestDTO;
import com.cefet.subip_mg_backend.dto.BibliotecaResponseDTO;
import com.cefet.subip_mg_backend.entities.Biblioteca;
import com.cefet.subip_mg_backend.exceptions.DatabaseException;
import com.cefet.subip_mg_backend.exceptions.ResourceNotFoundException;
import com.cefet.subip_mg_backend.repositories.BibliotecaRepository;
import com.cefet.subip_mg_backend.repositories.ExemplarRepository;

@Service
public class BibliotecaService {

	@Autowired
	private BibliotecaRepository bibliotecaRepository;

	@Autowired
	private ExemplarRepository exemplarRepository;

	@Transactional(readOnly = true)
	public List<BibliotecaResponseDTO> listar() {
		List<Biblioteca> lista = bibliotecaRepository.findAll();
		return lista.stream().map(BibliotecaResponseDTO::new).toList();
	}

	@Transactional(readOnly = true)
	public BibliotecaResponseDTO buscarPorId(Long id) {
		Biblioteca entity = bibliotecaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Biblioteca nao encontrada. Id: " + id));

		return new BibliotecaResponseDTO(entity);
	}

	@Transactional
	public BibliotecaResponseDTO inserir(BibliotecaRequestDTO dto) {
		Biblioteca entity = new Biblioteca();
		copiarDtoParaEntidade(dto, entity);
		entity = bibliotecaRepository.save(entity);

		return new BibliotecaResponseDTO(entity);
	}

	@Transactional
	public BibliotecaResponseDTO alterar(Long id, BibliotecaRequestDTO dto) {
		Biblioteca entity = bibliotecaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Biblioteca nao encontrada. Id: " + id));

		copiarDtoParaEntidade(dto, entity);
		entity = bibliotecaRepository.save(entity);

		return new BibliotecaResponseDTO(entity);
	}

	@Transactional
	public void excluir(Long id) {
		if (!bibliotecaRepository.existsById(id)) {
			throw new ResourceNotFoundException("Biblioteca nao encontrada. Id: " + id);
		}

		if (exemplarRepository.existsByBibliotecaId(id)) {
			throw new DatabaseException("Nao e possivel excluir uma biblioteca que possui exemplares cadastrados.");
		}

		bibliotecaRepository.deleteById(id);
	}

	private void copiarDtoParaEntidade(BibliotecaRequestDTO dto, Biblioteca entity) {
		entity.setNome(dto.getNome());
	}
}
