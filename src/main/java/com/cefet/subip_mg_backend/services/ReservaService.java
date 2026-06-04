package com.cefet.subip_mg_backend.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cefet.subip_mg_backend.dto.ReservaRequestDTO;
import com.cefet.subip_mg_backend.dto.ReservaResponseDTO;
import com.cefet.subip_mg_backend.entities.Exemplar;
import com.cefet.subip_mg_backend.entities.Pessoa;
import com.cefet.subip_mg_backend.entities.Reserva;
import com.cefet.subip_mg_backend.enums.SituacaoExemplar;
import com.cefet.subip_mg_backend.enums.SituacaoReserva;
import com.cefet.subip_mg_backend.exceptions.DatabaseException;
import com.cefet.subip_mg_backend.exceptions.ResourceNotFoundException;
import com.cefet.subip_mg_backend.repositories.ExemplarRepository;
import com.cefet.subip_mg_backend.repositories.PessoaRepository;
import com.cefet.subip_mg_backend.repositories.ReservaRepository;

@Service
public class ReservaService {

	@Autowired
	private ReservaRepository reservaRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private ExemplarRepository exemplarRepository;

	@Transactional(readOnly = true)
	public List<ReservaResponseDTO> listar() {
		List<Reserva> lista = reservaRepository.findAll();
		return lista.stream().map(ReservaResponseDTO::new).toList();
	}

	@Transactional(readOnly = true)
	public ReservaResponseDTO buscarPorId(Long id) {
		Reserva entity = reservaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Reserva nao encontrada. Id: " + id));

		return new ReservaResponseDTO(entity);
	}

	@Transactional
	public ReservaResponseDTO registrar(ReservaRequestDTO dto) {
		Pessoa pessoa = pessoaRepository.findById(dto.getPessoaId())
				.orElseThrow(() -> new ResourceNotFoundException("Pessoa nao encontrada. Id: " + dto.getPessoaId()));

		Exemplar exemplar = exemplarRepository.findById(dto.getExemplarId())
				.orElseThrow(() -> new ResourceNotFoundException("Exemplar nao encontrado. Id: " + dto.getExemplarId()));

		validarReserva(dto, exemplar);

		Reserva entity = new Reserva();
		entity.setSituacao(SituacaoReserva.ATIVA);
		entity.setDataRegistro(LocalDate.now());
		entity.setPessoa(pessoa);
		entity.setExemplar(exemplar);
		entity = reservaRepository.save(entity);

		return new ReservaResponseDTO(entity);
	}

	@Transactional
	public ReservaResponseDTO cancelar(Long id) {
		Reserva entity = reservaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Reserva nao encontrada. Id: " + id));

		if (entity.getSituacao() != SituacaoReserva.ATIVA) {
			throw new DatabaseException("Somente reservas ativas podem ser canceladas.");
		}

		entity.setSituacao(SituacaoReserva.CANCELADA);

		return new ReservaResponseDTO(entity);
	}

	private void validarReserva(ReservaRequestDTO dto, Exemplar exemplar) {
		if (exemplar.getSituacao() == SituacaoExemplar.INDISPONIVEL) {
			throw new DatabaseException("Nao e possivel reservar exemplar indisponivel.");
		}

		if (exemplar.getSituacao() != SituacaoExemplar.EMPRESTADO) {
			throw new DatabaseException("So e possivel reservar exemplar emprestado.");
		}

		if (reservaRepository.existsByPessoaIdAndExemplarIdAndSituacao(
				dto.getPessoaId(), dto.getExemplarId(), SituacaoReserva.ATIVA)) {
			throw new DatabaseException("Pessoa ja possui reserva ativa para este exemplar.");
		}
	}
}
