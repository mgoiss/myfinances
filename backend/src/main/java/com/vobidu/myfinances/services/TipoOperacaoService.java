package com.vobidu.myfinances.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vobidu.myfinances.DTO.TipoOperacaoDTO;
import com.vobidu.myfinances.entities.TipoOperacao;
import com.vobidu.myfinances.repositories.TipoOperacaoRepositorie;
import com.vobidu.myfinances.services.exceptions.EntidadeNaoEncontradaExcecao;

@Service
public class TipoOperacaoService {
	
	@Autowired
	private TipoOperacaoRepositorie repository;
	
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
		TipoOperacao tipoOperacao = new TipoOperacao();
		tipoOperacao.setNome(dto.getNome());
		tipoOperacao = repository.save(tipoOperacao);
		return new TipoOperacaoDTO(tipoOperacao);
	}
}
