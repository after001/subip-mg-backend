package com.cefet.subip_mg_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.subip_mg_backend.entities.Reserva;
import com.cefet.subip_mg_backend.enums.SituacaoReserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
	boolean existsByPessoaId(Long pessoaId);
	boolean existsByExemplarId(Long exemplarId);
	boolean existsByPessoaIdAndExemplarIdAndSituacao(Long pessoaId, Long exemplarId, SituacaoReserva situacao);
}
