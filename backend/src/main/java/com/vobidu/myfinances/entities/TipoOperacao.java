package com.vobidu.myfinances.entities;

import java.time.Instant;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "tb_tipo_operacao")
public class TipoOperacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant DataCriacao;
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant DataAlteracao;
	
	public TipoOperacao() {
		
	}

	public TipoOperacao(Long id, String nome) {
		this.id = id;
		this.nome = nome;
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

	public Instant getDataCriacao() {
		return DataCriacao;
	}
	public Instant getDataAlteracao() {
		return DataAlteracao;
	}

	@PrePersist
	public void preCriacao() {
		DataCriacao = Instant.now();
	}
	@PreUpdate
	public void preAlteracao() {
		DataAlteracao = Instant.now();
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
		TipoOperacao other = (TipoOperacao) obj;
		return Objects.equals(id, other.id);
	}
}
