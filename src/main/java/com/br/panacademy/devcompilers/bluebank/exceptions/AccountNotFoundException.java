package com.br.panacademy.devcompilers.bluebank.exceptions;

import java.util.NoSuchElementException;

public class AccountNotFoundException extends NoSuchElementException {
	
	public AccountNotFoundException(Long id) {
		super(String.format("Conta com Id: %d não cadastrada!", id));
	}
}
