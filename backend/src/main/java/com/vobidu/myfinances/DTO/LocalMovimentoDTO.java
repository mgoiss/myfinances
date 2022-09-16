package com.vobidu.myfinances.DTO;

import java.io.Serializable;

import com.vobidu.myfinances.entities.LocalMovimento;
import com.vobidu.myfinances.entities.Usuario;

public class LocalMovimentoDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private Double saldo;

	private UsuarioDTO usuario;
	
	public LocalMovimentoDTO() {
	}

	public LocalMovimentoDTO(LocalMovimento entidade) {
		super();
		this.id = entidade.getId();
		this.nome = entidade.getNome();
		this.saldo = entidade.getSaldo();
	}
	
	public LocalMovimentoDTO(LocalMovimento entidade, Usuario usuario) {
		this(entidade);
		this.usuario = new UsuarioDTO(usuario);
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
	
	public UsuarioDTO getUsuario() {
		return this.usuario;
	}
}
