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

import com.vobidu.myfinances.DTO.LocalMovimentoDTO;
import com.vobidu.myfinances.entities.LocalMovimento;
import com.vobidu.myfinances.entities.Usuario;
import com.vobidu.myfinances.repositories.LocalMovimentoRepository;
import com.vobidu.myfinances.repositories.UsuarioRepository;
import com.vobidu.myfinances.services.exceptions.BancoDadosExcecao;
import com.vobidu.myfinances.services.exceptions.EntidadeNaoEncontradaExcecao;

@Service
public class LocalMovimentoService {
	
	@Autowired
	private LocalMovimentoRepository repository;
	@Autowired
	private UsuarioRepository usuarioRespository;
	
	@Transactional(readOnly = true)
	public Page<LocalMovimentoDTO> buscarTodos(PageRequest requisicaoPaginada){
		Page<LocalMovimento> lista = repository.findAll(requisicaoPaginada);
		return lista.map(x -> new LocalMovimentoDTO(x, x.getUsuario()));
	}
	
	@Transactional(readOnly = true)
	public LocalMovimentoDTO buscarPorId(Long id) {
		Optional<LocalMovimento> obj = repository.findById(id);
		LocalMovimento entidade = obj.orElseThrow(() -> new EntidadeNaoEncontradaExcecao("LocalMovimento não encontrada"));
		return new LocalMovimentoDTO(entidade, entidade.getUsuario());
	}

	@Transactional
	public LocalMovimentoDTO inserir(LocalMovimentoDTO dto) {
		LocalMovimento entidade = new LocalMovimento();
		copiarDtoParaEntidade(dto, entidade);
		entidade = repository.save(entidade);
		return new LocalMovimentoDTO(entidade, entidade.getUsuario());
	}
	
	@Transactional
	public LocalMovimentoDTO alterar(Long id, LocalMovimentoDTO dto) {
		try {
			LocalMovimento entidade = repository.getReferenceById(id);
			copiarDtoParaEntidade(dto, entidade);
			entidade = repository.save(entidade);
			return new LocalMovimentoDTO(entidade, entidade.getUsuario());
		}
		catch (EntityNotFoundException e) {
			throw new EntidadeNaoEncontradaExcecao("LocalMovimento com id " + id + " não encontrada");
		}
	}
	
	public void deletar(Long id) {
		try {
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaExcecao("LocalMovimento com id " + id + " não encontrada");
		}
		catch (DataIntegrityViolationException e) {
			throw new BancoDadosExcecao("Violação de integridade");
		}
	}
	
	private void copiarDtoParaEntidade(LocalMovimentoDTO dto, LocalMovimento entidade) {		
		entidade.setNome(dto.getNome());
		entidade.setSaldo(dto.getSaldo());
		
		Usuario usuario = usuarioRespository.getReferenceById(dto.getUsuario().getId());
		entidade.setUsuario(usuario);
	}
}
