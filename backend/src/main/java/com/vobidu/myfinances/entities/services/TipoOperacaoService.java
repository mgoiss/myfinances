package com.vobidu.myfinances.entities.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vobidu.myfinances.entities.TipoOperacao;
import com.vobidu.myfinances.repositories.TipoOperacaoRepositorie;

@Service
public class TipoOperacaoService {
	
	@Autowired
	private TipoOperacaoRepositorie repository;
	
	public List<TipoOperacao> findAll(){
		return repository.findAll();
	}
}
