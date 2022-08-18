package com.vobidu.myfinances.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vobidu.myfinances.entities.TipoOperacao;
import com.vobidu.myfinances.entities.services.TipoOperacaoService;

@RestController
@RequestMapping(value = "/tipoOperacoes")
public class TipoOperacaoResource {
	
	@Autowired
	private TipoOperacaoService service;
	
	@GetMapping
	public ResponseEntity<List<TipoOperacao>> findAll() {
		List<TipoOperacao> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
	}
}
