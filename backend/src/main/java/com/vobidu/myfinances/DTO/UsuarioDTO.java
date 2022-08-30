package com.vobidu.myfinances.DTO;

import com.vobidu.myfinances.entities.Usuario;

public class UsuarioDTO {

	private Long id;
	private String nome;
	private String email;
	private String senha;
	
	public UsuarioDTO() {
		
	}
	
	public UsuarioDTO(Long id, String nome, String email, String senha) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}
	
	public UsuarioDTO(Usuario entidade) {
		this.id = entidade.getId();
		this.nome = entidade.getNome();
		this.email = entidade.getEmail();
		this.senha = entidade.getSenha();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
