package com.vobidu.myfinances.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//ADICIONAR UMA OPÇÃO DE LOCAL HABILITADO OU NÃO, POIS NÃO DEVE PODER APAGAR UMA CONTA, POR CONTA DOS MOVIMENTO 

@Entity
@Table(name = "tb_local_movimento")
public class LocalMovimento implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Double saldo;
	private Boolean status;
	
	@ManyToOne
	private Usuario usuario;
	
	public LocalMovimento() {
	}

	public LocalMovimento(Long id, String nome, Double saldo, Boolean status) {
		super();
		this.id = id;
		this.nome = nome;
		this.saldo = saldo;
		this.status = status;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void recalcularSaldo(Double valorNovoMovimento, String tipoOperacao) {		
		if (tipoOperacao.toUpperCase().intern() == "RECEITA") {
			this.saldo += valorNovoMovimento;
		}
		else {
			this.saldo -= valorNovoMovimento;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LocalMovimento other = (LocalMovimento) obj;
		return Objects.equals(id, other.id);
	}	
}
