package com.vobidu.myfinances.services.exceptions;

public class EntidadeNaoEncontradaExcecao extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public EntidadeNaoEncontradaExcecao(String msg) {
		super(msg);
	}
}
