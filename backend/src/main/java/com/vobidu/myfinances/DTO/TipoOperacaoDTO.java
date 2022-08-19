package com.vobidu.myfinances.DTO;

import com.vobidu.myfinances.entities.TipoOperacao;

public class TipoOperacaoDTO {

	private Long id;
	private String nome;
	
	public TipoOperacaoDTO() {
	}

	public TipoOperacaoDTO(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public TipoOperacaoDTO(TipoOperacao entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
