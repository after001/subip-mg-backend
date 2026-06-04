package com.cefet.subip_mg_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cefet.subip_mg_backend.dto.EmprestimoResponseDTO;
import com.cefet.subip_mg_backend.dto.PessoaRequestDTO;
import com.cefet.subip_mg_backend.dto.PessoaResponseDTO;
import com.cefet.subip_mg_backend.entities.Emprestimo;
import com.cefet.subip_mg_backend.entities.Pessoa;
import com.cefet.subip_mg_backend.exceptions.DatabaseException;
import com.cefet.subip_mg_backend.exceptions.ResourceNotFoundException;
import com.cefet.subip_mg_backend.repositories.EmprestimoRepository;
import com.cefet.subip_mg_backend.repositories.PessoaRepository;
import com.cefet.subip_mg_backend.repositories.ReservaRepository;
import com.cefet.subip_mg_backend.repositories.UsuarioRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private EmprestimoRepository emprestimoRepository;

	@Autowired
	private ReservaRepository reservaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Transactional(readOnly = true)
	public List<PessoaResponseDTO> listar() {
		List<Pessoa> lista = pessoaRepository.findAll();
		return lista.stream().map(PessoaResponseDTO::new).toList();
	}

	@Transactional(readOnly = true)
	public PessoaResponseDTO buscarPorId(Long id) {
		Pessoa entity = pessoaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Pessoa nao encontrada. Id: " + id));

		return new PessoaResponseDTO(entity);
	}

	@Transactional(readOnly = true)
	public List<EmprestimoResponseDTO> listarEmprestimos(Long id) {
		// Diferencia pessoa inexistente de pessoa existente sem emprestimos.
		if (!pessoaRepository.existsById(id)) {
			throw new ResourceNotFoundException("Pessoa nao encontrada. Id: " + id);
		}

		List<Emprestimo> lista = emprestimoRepository.findByPessoaId(id);
		return lista.stream().map(EmprestimoResponseDTO::new).toList();
	}

	@Transactional
	public PessoaResponseDTO inserir(PessoaRequestDTO dto) {
		if (pessoaRepository.existsByCpf(dto.getCpf())) {
			throw new DatabaseException("CPF ja cadastrado.");
		}

		if (pessoaRepository.existsByEmail(dto.getEmail())) {
			throw new DatabaseException("Email ja cadastrado.");
		}

		Pessoa entity = new Pessoa();
		copiarDtoParaEntidade(dto, entity);
		entity = pessoaRepository.save(entity);

		return new PessoaResponseDTO(entity);
	}

	@Transactional
	public PessoaResponseDTO alterar(Long id, PessoaRequestDTO dto) {
		Pessoa entity = pessoaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Pessoa nao encontrada. Id: " + id));

		if (pessoaRepository.existsByCpf(dto.getCpf()) && !entity.getCpf().equals(dto.getCpf())) {
			throw new DatabaseException("CPF ja cadastrado.");
		}

		if (pessoaRepository.existsByEmail(dto.getEmail()) && !entity.getEmail().equals(dto.getEmail())) {
			throw new DatabaseException("Email ja cadastrado.");
		}

		copiarDtoParaEntidade(dto, entity);
		entity = pessoaRepository.save(entity);

		return new PessoaResponseDTO(entity);
	}

	@Transactional
	public void excluir(Long id) {
		if (!pessoaRepository.existsById(id)) {
			throw new ResourceNotFoundException("Pessoa nao encontrada. Id: " + id);
		}

		if (emprestimoRepository.existsByPessoaId(id)) {
			throw new DatabaseException("Nao e possivel excluir uma pessoa que possui emprestimos cadastrados.");
		}

		if (reservaRepository.existsByPessoaId(id)) {
			throw new DatabaseException("Nao e possivel excluir uma pessoa que possui reservas cadastradas.");
		}

		if (usuarioRepository.existsByPessoaId(id)) {
			throw new DatabaseException("Nao e possivel excluir uma pessoa que possui usuario cadastrado.");
		}

		pessoaRepository.deleteById(id);
	}

	private void copiarDtoParaEntidade(PessoaRequestDTO dto, Pessoa entity) {
		entity.setNome(dto.getNome());
		entity.setCpf(dto.getCpf());
		entity.setEmail(dto.getEmail());
	}
}
