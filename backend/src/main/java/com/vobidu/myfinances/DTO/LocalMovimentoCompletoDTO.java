package com.vobidu.myfinances.DTO;

import com.vobidu.myfinances.entities.LocalMovimento;
import com.vobidu.myfinances.entities.Usuario;

public class LocalMovimentoCompletoDTO extends LocalMovimentoDTO{
	private static final long serialVersionUID = 1L;
	
	private UsuarioDTO usuario;
	
	//Estudar uma forma de criar um construtor
	public LocalMovimentoCompletoDTO(LocalMovimento entidade, Usuario usuario) {
		super(entidade);
		this.usuario = new UsuarioDTO(usuario);
	}
	
	public LocalMovimentoCompletoDTO() {
	}
	
	public UsuarioDTO getUsuario() {
		return this.usuario;
	}
}
