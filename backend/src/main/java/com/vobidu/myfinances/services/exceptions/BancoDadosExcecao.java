package com.vobidu.myfinances.services.exceptions;

public class BancoDadosExcecao extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public BancoDadosExcecao(String msg) {
		super(msg);
	}
}
