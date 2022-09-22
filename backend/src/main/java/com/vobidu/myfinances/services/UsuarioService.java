package com.vobidu.myfinances.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vobidu.myfinances.DTO.UsuarioAtualizarDTO;
import com.vobidu.myfinances.DTO.UsuarioDTO;
import com.vobidu.myfinances.DTO.UsuarioInserirDTO;
import com.vobidu.myfinances.entities.Usuario;
import com.vobidu.myfinances.repositories.UsuarioRepository;
import com.vobidu.myfinances.services.exceptions.BancoDadosExcecao;
import com.vobidu.myfinances.services.exceptions.EntidadeNaoEncontradaExcecao;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Transactional(readOnly = true)
	public Page<UsuarioDTO> buscarTodos(PageRequest requisicaoPaginada){
		Page<Usuario> lista = repository.findAll(requisicaoPaginada);
		return lista.map(x -> new UsuarioDTO(x));
	}
	
	@Transactional(readOnly = true)
	public UsuarioDTO buscarPorId(Long id) {
		Optional<Usuario> obj = repository.findById(id);
		Usuario entidade = obj.orElseThrow(() -> new EntidadeNaoEncontradaExcecao("Usuario não encontrada"));
		return new UsuarioDTO(entidade);
	}

	@Transactional
	public UsuarioDTO inserir(UsuarioInserirDTO dto) {
		Usuario entidade = new Usuario();
		copiaDtoParaEntidade(dto, entidade);
		
		//Aqui entra a criptografia da senha
		entidade.setSenha(dto.getSenha());
		
		entidade = repository.save(entidade);
		return new UsuarioDTO(entidade);
	}
	
	@Transactional
	public UsuarioDTO alterar(Long id, UsuarioAtualizarDTO dto) {
		try {
			Usuario entidade = repository.getReferenceById(id);
			copiaDtoParaEntidade(dto, entidade);
			//Essa função não atualiza a senha
			entidade = repository.save(entidade);
			return new UsuarioDTO(entidade);
		}
		catch (EntityNotFoundException e) {
			throw new EntidadeNaoEncontradaExcecao("Usuario com id " + id + " não encontrada");
		}
	}
	
	public void deletar(Long id) {
		try {
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaExcecao("Usuario com id " + id + " não encontrada");
		}
		catch (DataIntegrityViolationException e) {
			throw new BancoDadosExcecao("Violação de integridade");
		}
	}
	
	private void copiaDtoParaEntidade(UsuarioDTO dto, Usuario entidade) {
		entidade.setNome(dto.getNome());
		entidade.setEmail(dto.getEmail());
	}
}
