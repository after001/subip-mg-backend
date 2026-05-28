package com.cefet.subip_mg_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cefet.subip_mg_backend.dto.EmprestimoRequestDTO;
import com.cefet.subip_mg_backend.dto.EmprestimoResponseDTO;
import com.cefet.subip_mg_backend.entities.Emprestimo;
import com.cefet.subip_mg_backend.entities.Exemplar;
import com.cefet.subip_mg_backend.entities.Pessoa;
import com.cefet.subip_mg_backend.enums.SituacaoEmprestimo;
import com.cefet.subip_mg_backend.enums.SituacaoExemplar;
import com.cefet.subip_mg_backend.exceptions.DatabaseException;
import com.cefet.subip_mg_backend.exceptions.ResourceNotFoundException;
import com.cefet.subip_mg_backend.repositories.EmprestimoRepository;
import com.cefet.subip_mg_backend.repositories.ExemplarRepository;
import com.cefet.subip_mg_backend.repositories.PessoaRepository;

@Service
public class EmprestimoService {

	@Autowired
	private EmprestimoRepository emprestimoRepository;

	@Autowired
	private ExemplarRepository exemplarRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	@Transactional(readOnly = true)
	public List<EmprestimoResponseDTO> listar() {
		List<Emprestimo> lista = emprestimoRepository.findAll();
		return lista.stream().map(EmprestimoResponseDTO::new).toList();
	}

	@Transactional(readOnly = true)
	public EmprestimoResponseDTO buscarPorId(Long id) {
		Emprestimo entity = emprestimoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Emprestimo nao encontrado. Id: " + id));

		return new EmprestimoResponseDTO(entity);
	}

	@Transactional
	public EmprestimoResponseDTO registrar(EmprestimoRequestDTO dto) {
		validarDatas(dto);

		Exemplar exemplar = exemplarRepository.findById(dto.getExemplarId())
				.orElseThrow(() -> new ResourceNotFoundException("Exemplar nao encontrado. Id: " + dto.getExemplarId()));

		Pessoa pessoa = pessoaRepository.findById(dto.getPessoaId())
				.orElseThrow(() -> new ResourceNotFoundException("Pessoa nao encontrada. Id: " + dto.getPessoaId()));

		if (exemplar.getSituacao() != SituacaoExemplar.DISPONIVEL) {
			throw new DatabaseException("Esse exemplar nao esta disponivel no momento.");
		}

		Emprestimo entity = new Emprestimo();
		entity.setSituacao(SituacaoEmprestimo.EM_ANDAMENTO);
		entity.setDataRetirada(dto.getDataRetirada());
		entity.setDataDevolucaoPrevista(dto.getDataDevolucaoPrevista());
		entity.setDataDevolucao(null);
		entity.setExemplar(exemplar);
		entity.setPessoa(pessoa);

		exemplar.setSituacao(SituacaoExemplar.EMPRESTADO);
		entity = emprestimoRepository.save(entity);

		return new EmprestimoResponseDTO(entity);
	}

	private void validarDatas(EmprestimoRequestDTO dto) {
		if (dto.getDataRetirada() != null
				&& dto.getDataDevolucaoPrevista() != null
				&& dto.getDataDevolucaoPrevista().isBefore(dto.getDataRetirada())) {
			throw new DatabaseException("A data de devolucao prevista nao pode ser anterior a data de retirada.");
		}
	}
}
