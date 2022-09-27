package com.vobidu.myfinances.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.vobidu.myfinances.DTO.UsuarioAtualizarDTO;
import com.vobidu.myfinances.DTO.UsuarioDTO;
import com.vobidu.myfinances.DTO.UsuarioInserirDTO;
import com.vobidu.myfinances.services.UsuarioService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService service;
	
	@GetMapping
	@ApiOperation(value = "Lista Usuarios")
	public ResponseEntity<Page<UsuarioDTO>> buscarTodos(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy
			) {
		PageRequest requisicaoPaginada = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<UsuarioDTO> listaUsuarioDTO = service.buscarTodos(requisicaoPaginada);
		
		return ResponseEntity.ok().body(listaUsuarioDTO);
	}
	
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Lista um Usuario")
	public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Long id) {
		UsuarioDTO dto = service.buscarPorId(id);
		
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	@ApiOperation(value = "Salva Usuarios")
	public ResponseEntity<UsuarioDTO> inserir(@RequestBody UsuarioInserirDTO dto) {
		UsuarioDTO usuarioDto = service.inserir(dto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(usuarioDto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(usuarioDto);
	}
	
	@PutMapping(value = "/{id}")
	@ApiOperation(value = "Altera Usuarios")
	public ResponseEntity<UsuarioDTO> alterar(@PathVariable Long id, @RequestBody UsuarioAtualizarDTO dto) {
		UsuarioDTO novoDto = service.alterar(id, dto);
		
		return ResponseEntity.ok().body(novoDto);
	}
	
	@DeleteMapping(value = "/{id}")
	@ApiOperation(value = "Deleta Usuarios")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
