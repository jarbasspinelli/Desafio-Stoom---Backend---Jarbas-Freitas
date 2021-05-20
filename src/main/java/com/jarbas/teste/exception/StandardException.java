package com.jarbas.teste.exception;

import lombok.Getter;

@Getter
public class StandardException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	private String messasge;
	
	public StandardException(String msg) {
		super("Not Found");
	}
}
