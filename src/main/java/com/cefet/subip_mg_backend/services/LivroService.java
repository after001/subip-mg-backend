package com.cefet.subip_mg_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cefet.subip_mg_backend.dto.ExemplarResponseDTO;
import com.cefet.subip_mg_backend.dto.LivroRequestDTO;
import com.cefet.subip_mg_backend.dto.LivroResponseDTO;
import com.cefet.subip_mg_backend.entities.Exemplar;
import com.cefet.subip_mg_backend.entities.Genero;
import com.cefet.subip_mg_backend.entities.Livro;
import com.cefet.subip_mg_backend.exceptions.DatabaseException;
import com.cefet.subip_mg_backend.exceptions.ResourceNotFoundException;
import com.cefet.subip_mg_backend.repositories.ExemplarRepository;
import com.cefet.subip_mg_backend.repositories.GeneroRepository;
import com.cefet.subip_mg_backend.repositories.LivroRepository;

@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;

	@Autowired
	private ExemplarRepository exemplarRepository;

	@Autowired
	private GeneroRepository generoRepository;

	@Transactional(readOnly = true)
	public List<LivroResponseDTO> listar(String titulo, String isbn) {
		List<Livro> lista = livroRepository.buscarPorFiltros(normalizarTexto(titulo), normalizarTexto(isbn));
		return lista.stream().map(LivroResponseDTO::new).toList();
	}

	@Transactional(readOnly = true)
	public LivroResponseDTO buscarPorId(Long id) {
		Livro entity = livroRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Livro nao encontrado. Id: " + id));

		return new LivroResponseDTO(entity);
	}

	@Transactional(readOnly = true)
	public List<ExemplarResponseDTO> listarExemplares(Long id) {
		// Diferencia livro inexistente de livro existente sem exemplares.
		if (!livroRepository.existsById(id)) {
			throw new ResourceNotFoundException("Livro nao encontrado. Id: " + id);
		}

		List<Exemplar> lista = exemplarRepository.findByLivroId(id);
		return lista.stream().map(ExemplarResponseDTO::new).toList();
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
		Genero genero = generoRepository.findById(dto.getGeneroId())
				.orElseThrow(() -> new ResourceNotFoundException("Genero nao encontrado. Id: " + dto.getGeneroId()));

		entity.setTitulo(dto.getTitulo());
		entity.setIsbn(dto.getIsbn());
		entity.setGenero(genero);
	}

	private String normalizarTexto(String valor) {
		if (valor == null || valor.isBlank()) {
			return null;
		}

		return valor.trim();
	}
}
