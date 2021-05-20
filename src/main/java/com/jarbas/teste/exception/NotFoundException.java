package com.jarbas.teste.exception;

public class NotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public NotFoundException(Object id) {
		super("id não encontrado:" + id);
	}

}