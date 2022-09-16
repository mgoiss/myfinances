package com.vobidu.myfinances.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.vobidu.myfinances.services.exceptions.BancoDadosExcecao;
import com.vobidu.myfinances.services.exceptions.EntidadeNaoEncontradaExcecao;

@ControllerAdvice //Annotation capiturar exceções
public class CapturadorExcecoesResource {

	@ExceptionHandler(EntidadeNaoEncontradaExcecao.class)
	public ResponseEntity<RetornoPadraoErro> entidadeNaoEncontrada(EntidadeNaoEncontradaExcecao e, HttpServletRequest request) {
		RetornoPadraoErro erro = new RetornoPadraoErro();
		HttpStatus status = HttpStatus.NOT_FOUND;
		erro.setInstante(Instant.now());
		erro.setStatus(status.value());
		erro.setErro("Recurso não encontrado");
		erro.setMensagem(e.getMessage());
		erro.setCaminho(request.getRequestURI());
		return ResponseEntity.status(status).body(erro);
	}
	
	@ExceptionHandler(BancoDadosExcecao.class)
	public ResponseEntity<RetornoPadraoErro> BancoDados(BancoDadosExcecao e, HttpServletRequest request) {
		RetornoPadraoErro erro = new RetornoPadraoErro();
		HttpStatus status = HttpStatus.BAD_REQUEST;
		erro.setInstante(Instant.now());
		erro.setStatus(status.value());
		erro.setErro("Integridade do Banco de Dados");
		erro.setMensagem(e.getMessage());
		erro.setCaminho(request.getRequestURI());
		return ResponseEntity.status(status).body(erro);
	}
}
