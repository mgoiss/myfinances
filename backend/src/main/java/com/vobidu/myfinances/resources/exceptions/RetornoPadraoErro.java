package com.vobidu.myfinances.resources.exceptions;

import java.io.Serializable;
import java.time.Instant;

public class RetornoPadraoErro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Instant instante;
	private Integer status;
	private String erro;
	private String mensagem;
	private String caminho;
	
	public RetornoPadraoErro() {
	}

	public Instant getInstante() {
		return instante;
	}

	public void setInstante(Instant instante) {
		this.instante = instante;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
}
