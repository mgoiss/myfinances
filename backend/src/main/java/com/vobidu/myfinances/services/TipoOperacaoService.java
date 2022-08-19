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

@Service
public class TipoOperacaoService {
	
	@Autowired
	private TipoOperacaoRepositorie repository;
	
	@Transactional(readOnly = true)
	public List<TipoOperacaoDTO> findAll(){
		//Pegando os tipo de operações no banco
		List<TipoOperacao> list = repository.findAll();
		//pecorrendo a list por meio do metodo map e assim alterando seu tipo para TipoOperacaoDTO
		return list.stream().map(x -> new TipoOperacaoDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public TipoOperacaoDTO findById(Long id) {
		//Pegando o tipo operacao especifico pelo id e passando para um objeto do tipo optional
		Optional<TipoOperacao> obj = repository.findById(id);
		//Passando para a entity do tipo TipoOperacao, caso exista
		TipoOperacao entity = obj.get();
		//Retornando 
		return new TipoOperacaoDTO(entity);
	}
}
