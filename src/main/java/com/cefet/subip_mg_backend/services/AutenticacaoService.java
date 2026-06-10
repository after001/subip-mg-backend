package com.cefet.subip_mg_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cefet.subip_mg_backend.dto.AutenticacaoRequestDTO;
import com.cefet.subip_mg_backend.dto.UsuarioResponseDTO;
import com.cefet.subip_mg_backend.entities.Usuario;
import com.cefet.subip_mg_backend.exceptions.DatabaseException;
import com.cefet.subip_mg_backend.repositories.UsuarioRepository;

@Service
public class AutenticacaoService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Transactional(readOnly = true)
	public UsuarioResponseDTO autenticar(AutenticacaoRequestDTO dto) {
		Usuario usuario = usuarioRepository.findByLogin(dto.getLogin())
				.orElseThrow(() -> new DatabaseException("Login ou senha invalidos."));

		if (!usuario.getSenha().equals(dto.getSenha())) {
			throw new DatabaseException("Login ou senha invalidos.");
		}

		return new UsuarioResponseDTO(usuario);
	}
}
