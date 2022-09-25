package com.vobidu.myfinances.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vobidu.myfinances.DTO.LocalMovimentoCompletoDTO;
import com.vobidu.myfinances.DTO.LocalMovimentoDTO;
import com.vobidu.myfinances.entities.LocalMovimento;
import com.vobidu.myfinances.entities.Usuario;
import com.vobidu.myfinances.repositories.LocalMovimentoRepository;
import com.vobidu.myfinances.repositories.UsuarioRepository;
import com.vobidu.myfinances.services.exceptions.EntidadeNaoEncontradaExcecao;

@Service
public class LocalMovimentoService {
	
	@Autowired
	private LocalMovimentoRepository repository;
	@Autowired
	private UsuarioRepository usuarioRespository;
	
	@Transactional(readOnly = true)
	public Page<LocalMovimentoDTO> buscarLocalMovimentoUsuario(PageRequest requisicaoPaginada, Long idUsuario, Optional<Boolean> status){
		Page<LocalMovimento> lista = repository.BuscarPorUsuario(requisicaoPaginada, idUsuario, status);

		return lista.map(x -> new LocalMovimentoDTO(x));
	}
	
	@Transactional(readOnly = true)
	public LocalMovimentoCompletoDTO buscarPorId(Long id) {
		Optional<LocalMovimento> obj = repository.findById(id);
		LocalMovimento entidade = obj.orElseThrow(() -> new EntidadeNaoEncontradaExcecao("LocalMovimento não encontrada"));
		return new LocalMovimentoCompletoDTO(entidade, entidade.getUsuario());
	}

	@Transactional
	public LocalMovimentoDTO inserir(LocalMovimentoCompletoDTO dto) {
		LocalMovimento entidade = new LocalMovimento();

		entidade.setNome(dto.getNome());
		entidade.setSaldo(dto.getSaldo());
		entidade.setStatus(dto.getStatus());
		Usuario usuario = usuarioRespository.getReferenceById(dto.getUsuario().getId());
		entidade.setUsuario(usuario);
		
		entidade = repository.save(entidade);
		return new LocalMovimentoDTO(entidade);
	}
	
	@Transactional
	public LocalMovimentoDTO alterar(Long id, LocalMovimentoCompletoDTO dto) {
		try {
			LocalMovimento entidade = repository.getReferenceById(id);

			entidade.setNome(dto.getNome());
			entidade.setStatus(dto.getStatus());
			
			entidade = repository.save(entidade);
			return new LocalMovimentoDTO(entidade);
		}
		catch (EntityNotFoundException e) {
			throw new EntidadeNaoEncontradaExcecao("LocalMovimento com id " + id + " não encontrada");
		}
	}
}
