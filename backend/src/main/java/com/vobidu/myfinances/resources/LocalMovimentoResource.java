package com.vobidu.myfinances.resources;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vobidu.myfinances.DTO.LocalMovimentoCompletoDTO;
import com.vobidu.myfinances.DTO.LocalMovimentoDTO;
import com.vobidu.myfinances.services.LocalMovimentoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/locaismovimento")
@CrossOrigin(origins = "*")
public class LocalMovimentoResource {
	
	@Autowired
	private LocalMovimentoService service;
	
	@GetMapping
	@ApiOperation(value = "Lista Local Movimento")
	public ResponseEntity<Page<LocalMovimentoDTO>> buscarLocalMovimentoUsuario(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam("idUsuario") Long idUsuario,
			@RequestParam("status") Optional<Boolean> status
			) {
		PageRequest requisicaoPaginada = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<LocalMovimentoDTO> listaLocalMovimentoDTO = service.buscarLocalMovimentoUsuario(requisicaoPaginada, idUsuario, status);
		
		return ResponseEntity.ok().body(listaLocalMovimentoDTO);
	}
	
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Lista um Local Movimento")
	public ResponseEntity<LocalMovimentoCompletoDTO> buscarPorId(@PathVariable Long id) {
		LocalMovimentoCompletoDTO dto = service.buscarPorId(id);
		
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	@ApiOperation(value = "Salva Local Movimento")
	public ResponseEntity<LocalMovimentoDTO> inserir(@RequestBody LocalMovimentoCompletoDTO dto) {
		LocalMovimentoDTO NovoDto = service.inserir(dto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(NovoDto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(NovoDto);
	}
	
	@PutMapping(value = "/{id}")
	@ApiOperation(value = "Deleta Local Movimento")
	public ResponseEntity<LocalMovimentoDTO> alterar(@PathVariable Long id, @RequestBody LocalMovimentoCompletoDTO dto) {
		LocalMovimentoDTO NovoDto = service.alterar(id, dto);
		
		return ResponseEntity.ok().body(NovoDto);
	}
}
