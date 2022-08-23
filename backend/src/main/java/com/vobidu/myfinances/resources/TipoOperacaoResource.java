package com.vobidu.myfinances.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vobidu.myfinances.DTO.TipoOperacaoDTO;
import com.vobidu.myfinances.services.TipoOperacaoService;

@RestController
@RequestMapping(value = "/tipoOperacoes")
public class TipoOperacaoResource {
	
	@Autowired
	private TipoOperacaoService service;
	
	@GetMapping
	public ResponseEntity<List<TipoOperacaoDTO>> buscarTodos() {
		List<TipoOperacaoDTO> listaTipoOperacaoDTO = service.buscarTodos();
		
		return ResponseEntity.ok().body(listaTipoOperacaoDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TipoOperacaoDTO> buscarPorId(@PathVariable Long id) {
		TipoOperacaoDTO dto = service.buscarPorId(id);
		
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<TipoOperacaoDTO> inserir(@RequestBody TipoOperacaoDTO dto) {
		dto = service.inserir(dto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(dto);
	}
}
