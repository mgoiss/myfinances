package com.vobidu.myfinances.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vobidu.myfinances.DTO.TipoOperacaoDTO;
import com.vobidu.myfinances.entities.TipoOperacao;
import com.vobidu.myfinances.repositories.TipoOperacaoRepository;
import com.vobidu.myfinances.services.exceptions.BancoDadosExcecao;
import com.vobidu.myfinances.services.exceptions.EntidadeNaoEncontradaExcecao;

@Service
public class TipoOperacaoService {
	
	@Autowired
	private TipoOperacaoRepository repository;
	
	@Transactional(readOnly = true)
	public List<TipoOperacaoDTO> buscarTodos(){
		List<TipoOperacao> lista = repository.findAll();
		return lista.stream().map(x -> new TipoOperacaoDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public TipoOperacaoDTO buscarPorId(Long id) {
		Optional<TipoOperacao> obj = repository.findById(id);
		TipoOperacao entidade = obj.orElseThrow(() -> new EntidadeNaoEncontradaExcecao("Tipo Operacao não encontrada"));
		return new TipoOperacaoDTO(entidade);
	}

	@Transactional
	public TipoOperacaoDTO inserir(TipoOperacaoDTO dto) {
		TipoOperacao entidade = new TipoOperacao();
		entidade.setNome(dto.getNome());
		entidade = repository.save(entidade);
		return new TipoOperacaoDTO(entidade);
	}
	
	@Transactional
	public TipoOperacaoDTO alterar(Long id, TipoOperacaoDTO dto) {
		try {
			TipoOperacao entidade = repository.getReferenceById(id);
			entidade.setNome(dto.getNome());
			entidade = repository.save(entidade);
			return new TipoOperacaoDTO(entidade);
		}
		catch (EntityNotFoundException e) {
			throw new EntidadeNaoEncontradaExcecao("Tipo Operacao com id " + id + " não encontrada");
		}
	}
	
	public void deletar(Long id) {
		try {
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaExcecao("Tipo Operacao com id " + id + " não encontrada");
		}
		catch (DataIntegrityViolationException e) {
			throw new BancoDadosExcecao("Violação de integridade");
		}
	}
}
