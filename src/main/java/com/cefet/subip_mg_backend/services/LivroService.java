package com.cefet.subip_mg_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cefet.subip_mg_backend.dto.LivroRequestDTO;
import com.cefet.subip_mg_backend.dto.LivroResponseDTO;
import com.cefet.subip_mg_backend.entities.Livro;
import com.cefet.subip_mg_backend.exceptions.DatabaseException;
import com.cefet.subip_mg_backend.exceptions.ResourceNotFoundException;
import com.cefet.subip_mg_backend.repositories.ExemplarRepository;
import com.cefet.subip_mg_backend.repositories.LivroRepository;

@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;

	@Autowired
	private ExemplarRepository exemplarRepository;

	@Transactional(readOnly = true)
	public List<LivroResponseDTO> listar() {
		List<Livro> lista = livroRepository.findAll();
		return lista.stream().map(LivroResponseDTO::new).toList();
	}

	@Transactional(readOnly = true)
	public LivroResponseDTO buscarPorId(Long id) {
		Livro entity = livroRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Livro nao encontrado. Id: " + id));

		return new LivroResponseDTO(entity);
	}

	@Transactional
	public LivroResponseDTO inserir(LivroRequestDTO dto) {
		if (livroRepository.existsByIsbn(dto.getIsbn())) {
			throw new DatabaseException("ISBN ja cadastrado.");
		}

		Livro entity = new Livro();
		copiarDtoParaEntidade(dto, entity);
		entity = livroRepository.save(entity);

		return new LivroResponseDTO(entity);
	}

	@Transactional
	public LivroResponseDTO alterar(Long id, LivroRequestDTO dto) {
		Livro entity = livroRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Livro nao encontrado. Id: " + id));

		if (livroRepository.existsByIsbn(dto.getIsbn()) && !entity.getIsbn().equals(dto.getIsbn())) {
			throw new DatabaseException("ISBN ja cadastrado.");
		}

		copiarDtoParaEntidade(dto, entity);
		entity = livroRepository.save(entity);

		return new LivroResponseDTO(entity);
	}

	@Transactional
	public void excluir(Long id) {
		if (!livroRepository.existsById(id)) {
			throw new ResourceNotFoundException("Livro nao encontrado. Id: " + id);
		}

		if (exemplarRepository.existsByLivroId(id)) {
			throw new DatabaseException("Nao e possivel excluir um livro que possui exemplares cadastrados.");
		}

		livroRepository.deleteById(id);
	}

	private void copiarDtoParaEntidade(LivroRequestDTO dto, Livro entity) {
		entity.setTitulo(dto.getTitulo());
		entity.setIsbn(dto.getIsbn());
	}
}
