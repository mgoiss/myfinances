package com.vobidu.myfinances.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vobidu.myfinances.DTO.LocalMovimentoDTO;
import com.vobidu.myfinances.services.LocalMovimentoService;

@RestController
@RequestMapping(value = "/locaismovimento")
public class LocalMovimentoResource {
	
	@Autowired
	private LocalMovimentoService service;
	
	@GetMapping
	public ResponseEntity<Page<LocalMovimentoDTO>> buscarTodos(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy
			) {
		PageRequest requisicaoPaginada = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<LocalMovimentoDTO> listaLocalMovimentoDTO = service.buscarTodos(requisicaoPaginada);
		
		return ResponseEntity.ok().body(listaLocalMovimentoDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<LocalMovimentoDTO> buscarPorId(@PathVariable Long id) {
		LocalMovimentoDTO dto = service.buscarPorId(id);
		
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<LocalMovimentoDTO> inserir(@RequestBody LocalMovimentoDTO dto) {
		dto = service.inserir(dto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<LocalMovimentoDTO> alterar(@PathVariable Long id, @RequestBody LocalMovimentoDTO dto) {
		dto = service.alterar(id, dto);
		
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
