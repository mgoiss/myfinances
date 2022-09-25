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

import com.vobidu.myfinances.DTO.MovimentoDTO;
import com.vobidu.myfinances.services.MovimentoService;

@RestController
@RequestMapping(value = "/movimentos")
public class MovimentoResource {
	
	@Autowired
	private MovimentoService service;
	
	@GetMapping
	public ResponseEntity<Page<MovimentoDTO>> buscarTodos(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "idUsuario", defaultValue = "1") Long idUsuario
			) {
		PageRequest requisicaoPaginada = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<MovimentoDTO> listaMovimentoDTO = service.buscarPorUsuario(requisicaoPaginada, idUsuario);
		
		return ResponseEntity.ok().body(listaMovimentoDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<MovimentoDTO> buscarPorId(@PathVariable Long id) {
		MovimentoDTO dto = service.buscarPorId(id);
		
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<MovimentoDTO> inserir(@RequestBody MovimentoDTO dto) {
		dto = service.inserir(dto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<MovimentoDTO> alterar(@PathVariable Long id, @RequestBody MovimentoDTO dto) {
		dto = service.alterar(id, dto);
		
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
