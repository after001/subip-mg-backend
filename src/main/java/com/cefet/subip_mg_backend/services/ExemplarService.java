package com.cefet.subip_mg_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cefet.subip_mg_backend.dto.ExemplarRequestDTO;
import com.cefet.subip_mg_backend.dto.ExemplarResponseDTO;
import com.cefet.subip_mg_backend.entities.Biblioteca;
import com.cefet.subip_mg_backend.entities.Exemplar;
import com.cefet.subip_mg_backend.entities.Livro;
import com.cefet.subip_mg_backend.exceptions.DatabaseException;
import com.cefet.subip_mg_backend.exceptions.ResourceNotFoundException;
import com.cefet.subip_mg_backend.repositories.BibliotecaRepository;
import com.cefet.subip_mg_backend.repositories.EmprestimoRepository;
import com.cefet.subip_mg_backend.repositories.ExemplarRepository;
import com.cefet.subip_mg_backend.repositories.LivroRepository;
import com.cefet.subip_mg_backend.repositories.ReservaRepository;

@Service
public class ExemplarService {

	@Autowired
	private ExemplarRepository exemplarRepository;

	@Autowired
	private LivroRepository livroRepository;

	@Autowired
	private BibliotecaRepository bibliotecaRepository;

	@Autowired
	private EmprestimoRepository emprestimoRepository;

	@Autowired
	private ReservaRepository reservaRepository;

	@Transactional(readOnly = true)
	public List<ExemplarResponseDTO> listar() {
		List<Exemplar> lista = exemplarRepository.findAll();
		return lista.stream().map(ExemplarResponseDTO::new).toList();
	}

	@Transactional(readOnly = true)
	public ExemplarResponseDTO buscarPorId(Long id) {
		Exemplar entity = exemplarRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Exemplar nao encontrado. Id: " + id));

		return new ExemplarResponseDTO(entity);
	}

	@Transactional
	public ExemplarResponseDTO inserir(ExemplarRequestDTO dto) {
		if (exemplarRepository.existsByTombo(dto.getTombo())) {
			throw new DatabaseException("Tombo ja cadastrado.");
		}

		Exemplar entity = new Exemplar();
		copiarDtoParaEntidade(dto, entity);
		entity = exemplarRepository.save(entity);

		return new ExemplarResponseDTO(entity);
	}

	@Transactional
	public ExemplarResponseDTO alterar(Long id, ExemplarRequestDTO dto) {
		Exemplar entity = exemplarRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Exemplar nao encontrado. Id: " + id));

		if (exemplarRepository.existsByTombo(dto.getTombo()) && !entity.getTombo().equals(dto.getTombo())) {
			throw new DatabaseException("Tombo ja cadastrado.");
		}

		copiarDtoParaEntidade(dto, entity);
		entity = exemplarRepository.save(entity);

		return new ExemplarResponseDTO(entity);
	}

	@Transactional
	public void excluir(Long id) {
		if (!exemplarRepository.existsById(id)) {
			throw new ResourceNotFoundException("Exemplar nao encontrado. Id: " + id);
		}

		if (emprestimoRepository.existsByExemplarId(id)) {
			throw new DatabaseException("Nao e possivel excluir um exemplar que possui emprestimos cadastrados.");
		}

		if (reservaRepository.existsByExemplarId(id)) {
			throw new DatabaseException("Nao e possivel excluir um exemplar que possui reservas cadastradas.");
		}

		exemplarRepository.deleteById(id);
	}

	private void copiarDtoParaEntidade(ExemplarRequestDTO dto, Exemplar entity) {
		Livro livro = livroRepository.findById(dto.getLivroId())
				.orElseThrow(() -> new ResourceNotFoundException("Livro nao encontrado. Id: " + dto.getLivroId()));

		Biblioteca biblioteca = bibliotecaRepository.findById(dto.getBibliotecaId())
				.orElseThrow(() -> new ResourceNotFoundException("Biblioteca nao encontrada. Id: " + dto.getBibliotecaId()));

		entity.setTombo(dto.getTombo());
		entity.setSituacao(dto.getSituacao());
		entity.setLivro(livro);
		entity.setBiblioteca(biblioteca);
	}
}
