package com.jarbas.teste.exception;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(StandardException.class)
	public ResponseEntity<StandarError> notFound(StandardException e, HttpServletRequest request) {
		String error = "id não encontrado";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandarError err = new StandarError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<StandarError> notFound(NotFoundException e, HttpServletRequest request) {
		String error = "id não encontrado";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandarError err = new StandarError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
