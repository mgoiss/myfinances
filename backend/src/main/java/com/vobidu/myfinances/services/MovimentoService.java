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
	
	//Verificar se o local movimento tá ativo para associar a um movimento (VALIDAÇÃO)
	
	@Transactional(readOnly = true)
	public Page<MovimentoDTO> buscarPorUsuario(PageRequest requisicaoPaginada, Long idUsuario){
		Page<Movimento> lista = repository.BuscarPorUsuario(requisicaoPaginada, idUsuario);

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
		entidade = repository.save(entidade);
		return new MovimentoDTO(entidade, entidade.getLocalMovimento(), entidade.getCategoria(), entidade.getTipoOperacao());
	}
	
	@Transactional
	public MovimentoDTO alterar(Long id, MovimentoDTO dto) {
		try {
			Movimento entidade = repository.getReferenceById(id);
			copiarDtoParaEntidade(dto, entidade);
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
		Categoria categoria = categoriaRepository.getReferenceById(dto.getCategoria().getId());
		TipoOperacao tipoOperacao = tipoOperacaoRepository.getReferenceById(dto.getTipoOperacao().getId());
		
		//Recalculando o saldo
		localMovimento.recalcularSaldo(entidade.getValor(), tipoOperacao.getNome());
		
		entidade.setLocalMovimento(localMovimento);
		entidade.setCategoria(categoria);
		entidade.setTipoOperacao(tipoOperacao);
	}
}
