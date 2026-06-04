package com.cefet.subip_mg_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cefet.subip_mg_backend.dto.GeneroRequestDTO;
import com.cefet.subip_mg_backend.dto.GeneroResponseDTO;
import com.cefet.subip_mg_backend.entities.Genero;
import com.cefet.subip_mg_backend.exceptions.DatabaseException;
import com.cefet.subip_mg_backend.exceptions.ResourceNotFoundException;
import com.cefet.subip_mg_backend.repositories.GeneroRepository;
import com.cefet.subip_mg_backend.repositories.LivroRepository;

@Service
public class GeneroService {

	@Autowired
	private GeneroRepository generoRepository;

	@Autowired
	private LivroRepository livroRepository;

	@Transactional(readOnly = true)
	public List<GeneroResponseDTO> listar() {
		List<Genero> lista = generoRepository.findAll();
		return lista.stream().map(GeneroResponseDTO::new).toList();
	}

	@Transactional(readOnly = true)
	public GeneroResponseDTO buscarPorId(Long id) {
		Genero entity = generoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Genero nao encontrado. Id: " + id));

		return new GeneroResponseDTO(entity);
	}

	@Transactional
	public GeneroResponseDTO inserir(GeneroRequestDTO dto) {
		validarDescricaoDuplicada(dto.getDescricao());

		Genero entity = new Genero();
		copiarDtoParaEntidade(dto, entity);
		entity = generoRepository.save(entity);

		return new GeneroResponseDTO(entity);
	}

	@Transactional
	public GeneroResponseDTO alterar(Long id, GeneroRequestDTO dto) {
		Genero entity = generoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Genero nao encontrado. Id: " + id));

		if (!entity.getDescricao().equalsIgnoreCase(dto.getDescricao())) {
			validarDescricaoDuplicada(dto.getDescricao());
		}

		copiarDtoParaEntidade(dto, entity);
		entity = generoRepository.save(entity);

		return new GeneroResponseDTO(entity);
	}

	@Transactional
	public void excluir(Long id) {
		if (!generoRepository.existsById(id)) {
			throw new ResourceNotFoundException("Genero nao encontrado. Id: " + id);
		}

		if (livroRepository.existsByGeneroId(id)) {
			throw new DatabaseException("Nao e possivel excluir um genero que possui livros cadastrados.");
		}

		generoRepository.deleteById(id);
	}

	private void validarDescricaoDuplicada(String descricao) {
		if (generoRepository.existsByDescricaoIgnoreCase(descricao)) {
			throw new DatabaseException("Genero ja cadastrado.");
		}
	}

	private void copiarDtoParaEntidade(GeneroRequestDTO dto, Genero entity) {
		entity.setDescricao(dto.getDescricao());
	}
}
