package com.cefet.subip_mg_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cefet.subip_mg_backend.dto.UsuarioRequestDTO;
import com.cefet.subip_mg_backend.dto.UsuarioResponseDTO;
import com.cefet.subip_mg_backend.entities.Pessoa;
import com.cefet.subip_mg_backend.entities.Usuario;
import com.cefet.subip_mg_backend.exceptions.DatabaseException;
import com.cefet.subip_mg_backend.exceptions.ResourceNotFoundException;
import com.cefet.subip_mg_backend.repositories.PessoaRepository;
import com.cefet.subip_mg_backend.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	@Transactional(readOnly = true)
	public List<UsuarioResponseDTO> listar() {
		List<Usuario> lista = usuarioRepository.findAll();
		return lista.stream().map(UsuarioResponseDTO::new).toList();
	}

	@Transactional(readOnly = true)
	public UsuarioResponseDTO buscarPorId(Long id) {
		Usuario entity = buscarEntidadePorId(id);
		return new UsuarioResponseDTO(entity);
	}

	@Transactional
	public UsuarioResponseDTO inserir(UsuarioRequestDTO dto) {
		validarLoginDuplicado(dto.getLogin());
		validarPessoaComUsuario(dto.getPessoaId());

		Usuario entity = new Usuario();
		copiarDtoParaEntidade(dto, entity);
		entity = usuarioRepository.save(entity);

		return new UsuarioResponseDTO(entity);
	}

	@Transactional
	public UsuarioResponseDTO alterar(Long id, UsuarioRequestDTO dto) {
		Usuario entity = buscarEntidadePorId(id);

		if (!entity.getLogin().equals(dto.getLogin())) {
			validarLoginDuplicado(dto.getLogin());
		}

		if (!entity.getPessoa().getId().equals(dto.getPessoaId())) {
			validarPessoaComUsuario(dto.getPessoaId());
		}

		copiarDtoParaEntidade(dto, entity);
		entity = usuarioRepository.save(entity);

		return new UsuarioResponseDTO(entity);
	}

	@Transactional
	public void excluir(Long id) {
		if (!usuarioRepository.existsById(id)) {
			throw new ResourceNotFoundException("Usuario nao encontrado. Id: " + id);
		}

		usuarioRepository.deleteById(id);
	}

	private Usuario buscarEntidadePorId(Long id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario nao encontrado. Id: " + id));
	}

	private void validarLoginDuplicado(String login) {
		if (usuarioRepository.existsByLogin(login)) {
			throw new DatabaseException("Login ja cadastrado.");
		}
	}

	private void validarPessoaComUsuario(Long pessoaId) {
		if (usuarioRepository.existsByPessoaId(pessoaId)) {
			throw new DatabaseException("Pessoa ja possui usuario cadastrado.");
		}
	}

	private void copiarDtoParaEntidade(UsuarioRequestDTO dto, Usuario entity) {
		Pessoa pessoa = pessoaRepository.findById(dto.getPessoaId())
				.orElseThrow(() -> new ResourceNotFoundException("Pessoa nao encontrada. Id: " + dto.getPessoaId()));

		entity.setLogin(dto.getLogin());
		entity.setPerfil(dto.getPerfil());
		entity.setPessoa(pessoa);
	}
}
