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

import com.vobidu.myfinances.DTO.MovimentoDTO;
import com.vobidu.myfinances.entities.Categoria;
import com.vobidu.myfinances.entities.LocalMovimento;
import com.vobidu.myfinances.entities.Movimento;
import com.vobidu.myfinances.entities.TipoOperacao;
import com.vobidu.myfinances.repositories.CategoriaRepository;
import com.vobidu.myfinances.repositories.LocalMovimentoRepository;
import com.vobidu.myfinances.repositories.MovimentoRepository;
import com.vobidu.myfinances.repositories.TipoOperacaoRepository;
import com.vobidu.myfinances.services.exceptions.BancoDadosExcecao;
import com.vobidu.myfinances.services.exceptions.EntidadeNaoEncontradaExcecao;

@Service
public class MovimentoService {
	
	@Autowired
	private MovimentoRepository repository;
	@Autowired
	private LocalMovimentoRepository localMovimentoRespository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private TipoOperacaoRepository tipoOperacaoRepository;
	
	//Verificar se é necessário retornar o local movimento com os dados do usuário, caso contrario criar um DTO sem essa informação
	
	//Fazer essa paginação retornar apenas o de um determinado usuário
	@Transactional(readOnly = true)
	public Page<MovimentoDTO> buscarTodos(PageRequest requisicaoPaginada){
		Page<Movimento> lista = repository.findAll(requisicaoPaginada);
		return lista.map(x -> new MovimentoDTO(x, x.getLocalMovimento(), x.getCategoria(), x.getTipoOperacao()));
	}
	
	@Transactional(readOnly = true)
	public MovimentoDTO buscarPorId(Long id) {
		Optional<Movimento> obj = repository.findById(id);
		Movimento entidade = obj.orElseThrow(() -> new EntidadeNaoEncontradaExcecao("Movimento não encontrado"));
		return new MovimentoDTO(entidade, entidade.getLocalMovimento(), entidade.getCategoria(), entidade.getTipoOperacao());
	}

	@Transactional
	public MovimentoDTO inserir(MovimentoDTO dto) {
		Movimento entidade = new Movimento();
		copiarDtoParaEntidade(dto, entidade);
		//Chamar uma função para atualizar o saldo do local de movimento
		entidade = repository.save(entidade);
		return new MovimentoDTO(entidade, entidade.getLocalMovimento(), entidade.getCategoria(), entidade.getTipoOperacao());
	}
	
	@Transactional
	public MovimentoDTO alterar(Long id, MovimentoDTO dto) {
		try {
			Movimento entidade = repository.getReferenceById(id);
			copiarDtoParaEntidade(dto, entidade);
			//Chamar uma função para atualizar o saldo do local de movimento
			entidade = repository.save(entidade);
			return new MovimentoDTO(entidade, entidade.getLocalMovimento(), entidade.getCategoria(), entidade.getTipoOperacao());
		}
		catch (EntityNotFoundException e) {
			throw new EntidadeNaoEncontradaExcecao("Movimento com id " + id + " não encontrado");
		}
	}
	
	public void deletar(Long id) {
		try {
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaExcecao("Movimento com id " + id + " não encontrado");
		}
		catch (DataIntegrityViolationException e) {
			throw new BancoDadosExcecao("Violação de integridade");
		}
	}
	
	private void copiarDtoParaEntidade(MovimentoDTO dto, Movimento entidade) {		
		entidade.setDescricao(dto.getDescricao());
		entidade.setValor(dto.getValor());
		entidade.setData(dto.getData());
		
		LocalMovimento localMovimento = localMovimentoRespository.getReferenceById(dto.getLocalMovimento().getId());
		entidade.setLocalMovimento(localMovimento);
		
		Categoria categoria = categoriaRepository.getReferenceById(dto.getCategoria().getId());
		entidade.setCategoria(categoria);
		
		TipoOperacao tipoOperacao = tipoOperacaoRepository.getReferenceById(dto.getTipoOperacao().getId());
		entidade.setTipoOperacao(tipoOperacao);
	}
}