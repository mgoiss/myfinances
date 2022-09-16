package com.vobidu.myfinances.DTO;

public class UsuarioInserirAtualizarDTO extends UsuarioDTO{
	private static final long serialVersionUID = 1L;

	private String senha;
	
	public UsuarioInserirAtualizarDTO() {
		super();
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
