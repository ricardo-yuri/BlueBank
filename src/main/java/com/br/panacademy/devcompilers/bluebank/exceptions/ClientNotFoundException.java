package com.br.panacademy.devcompilers.bluebank.exceptions;

import java.util.NoSuchElementException;

public class ClientNotFoundException extends NoSuchElementException {

	public ClientNotFoundException(Long id) {
		super(String.format("Cliente com ID: %d não encontrado!", id));
	}
	
}
