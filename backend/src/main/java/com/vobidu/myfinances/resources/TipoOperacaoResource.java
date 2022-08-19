package com.vobidu.myfinances.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vobidu.myfinances.DTO.TipoOperacaoDTO;
import com.vobidu.myfinances.services.TipoOperacaoService;

@RestController
@RequestMapping(value = "/tipoOperacoes")
public class TipoOperacaoResource {
	
	@Autowired
	private TipoOperacaoService service;
	
	@GetMapping
	public ResponseEntity<List<TipoOperacaoDTO>> findAll() {
		List<TipoOperacaoDTO> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TipoOperacaoDTO> findById(@PathVariable Long id) {
		TipoOperacaoDTO dto = service.findById(id);
		
		return ResponseEntity.ok().body(dto);
	}
}
