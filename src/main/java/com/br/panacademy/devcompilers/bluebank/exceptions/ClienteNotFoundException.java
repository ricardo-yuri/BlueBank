package com.br.panacademy.devcompilers.bluebank.exceptions;

import java.util.NoSuchElementException;

public class ClienteNotFoundException extends NoSuchElementException {

	public ClienteNotFoundException(Long id) {
		super(String.format("Cliente com ID: %d não encontrado!", id));
	}
	
}
