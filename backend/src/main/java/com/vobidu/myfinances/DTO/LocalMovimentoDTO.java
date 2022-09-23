package com.vobidu.myfinances.DTO;

import java.io.Serializable;

import com.vobidu.myfinances.entities.LocalMovimento;

public class LocalMovimentoDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private Double saldo;
	private Boolean status;
	
	public LocalMovimentoDTO() {
	}

	public LocalMovimentoDTO(LocalMovimento entidade) {
		this.id = entidade.getId();
		this.nome = entidade.getNome();
		this.saldo = entidade.getSaldo();
		this.status = entidade.getStatus();
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

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
}
