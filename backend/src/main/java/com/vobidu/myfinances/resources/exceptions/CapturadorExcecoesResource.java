package com.vobidu.myfinances.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.vobidu.myfinances.services.exceptions.EntidadeNaoEncontradaExcecao;

@ControllerAdvice //Annotation capiturar exceções
public class CapturadorExcecoesResource {

	@ExceptionHandler(EntidadeNaoEncontradaExcecao.class)
	public ResponseEntity<RetornoPadraoErro> entidadeNaoEncontrada(EntidadeNaoEncontradaExcecao e, HttpServletRequest request) {
		RetornoPadraoErro erro = new RetornoPadraoErro();
		erro.setInstante(Instant.now());
		erro.setStatus(HttpStatus.NOT_FOUND.value());
		erro.setErro("Recurso não encontrado");
		erro.setMensagem(e.getMessage());
		erro.setCaminho(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
}
