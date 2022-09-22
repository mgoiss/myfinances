package com.vobidu.myfinances.DTO;

import java.time.Instant;

import com.vobidu.myfinances.entities.Categoria;
import com.vobidu.myfinances.entities.LocalMovimento;
import com.vobidu.myfinances.entities.Movimento;
import com.vobidu.myfinances.entities.TipoOperacao;

public class MovimentoDTO {

	private Long id;
	private String descricao;
	private Double valor;
	private Instant data;
	
	private LocalMovimentoDTO localMovimento;
	private CategoriaDTO categoria;
	private TipoOperacaoDTO tipoOperacao;
	
	public MovimentoDTO() {
	}

	public MovimentoDTO(Long id, String descricao, Double valor, Instant data) {
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
		this.data = data;
	}
	
	public MovimentoDTO(Movimento entidade) {
		this.id = entidade.getId();
		this.descricao = entidade.getDescricao();
		this.valor = entidade.getValor();
		this.data = entidade.getData();
	}
	
	public MovimentoDTO(Movimento entidade, LocalMovimento localMovimento, Categoria categoria, TipoOperacao tipoOperacao) {
		this(entidade);
		this.localMovimento = new LocalMovimentoDTO(localMovimento);
		this.categoria = new CategoriaDTO(categoria);
		this.tipoOperacao = new TipoOperacaoDTO(tipoOperacao);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Instant getData() {
		return data;
	}

	public void setData(Instant data) {
		this.data = data;
	}

	public LocalMovimentoDTO getLocalMovimento() {
		return localMovimento;
	}

	public void setLocalMovimento(LocalMovimentoDTO localMovimento) {
		this.localMovimento = localMovimento;
	}

	public CategoriaDTO getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDTO categoria) {
		this.categoria = categoria;
	}

	public TipoOperacaoDTO getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(TipoOperacaoDTO tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}
}
