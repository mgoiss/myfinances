package com.vobidu.myfinances.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vobidu.myfinances.DTO.CategoriaDTO;
import com.vobidu.myfinances.entities.Categoria;
import com.vobidu.myfinances.repositories.CategoriaRepositorie;
import com.vobidu.myfinances.services.exceptions.BancoDadosExcecao;
import com.vobidu.myfinances.services.exceptions.EntidadeNaoEncontradaExcecao;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepositorie repository;
	
	@Transactional(readOnly = true)
	public Page<CategoriaDTO> buscarTodos(PageRequest requisicaoPaginada){
		Page<Categoria> lista = repository.findAll(requisicaoPaginada);
		return lista.map(x -> new CategoriaDTO(x));
	}
	
	@Transactional(readOnly = true)
	public CategoriaDTO buscarPorId(Long id) {
		Optional<Categoria> obj = repository.findById(id);
		Categoria entidade = obj.orElseThrow(() -> new EntidadeNaoEncontradaExcecao("Categoria não encontrada"));
		return new CategoriaDTO(entidade);
	}

	@Transactional
	public CategoriaDTO inserir(CategoriaDTO dto) {
		Categoria entidade = new Categoria();
		entidade.setNome(dto.getNome());
		entidade = repository.save(entidade);
		return new CategoriaDTO(entidade);
	}
	
	@Transactional
	public CategoriaDTO alterar(Long id, CategoriaDTO dto) {
		try {
			Categoria entidade = repository.getReferenceById(id);
			entidade.setNome(dto.getNome());
			entidade = repository.save(entidade);
			return new CategoriaDTO(entidade);
		}
		catch (EntityNotFoundException e) {
			throw new EntidadeNaoEncontradaExcecao("Categoria com id " + id + " não encontrada");
		}
	}
	
	public void deletar(Long id) {
		try {
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaExcecao("Categoria com id " + id + " não encontrada");
		}
		catch (DataIntegrityViolationException e) {
			throw new BancoDadosExcecao("Violação de integridade");
		}
	}
}
