package com.vobidu.myfinances.DTO;

public class UsuarioInserirDTO extends UsuarioDTO{
	private static final long serialVersionUID = 1L;

	private String senha;
	
	public UsuarioInserirDTO() {
		super();
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
