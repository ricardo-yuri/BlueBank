package com.br.panacademy.devcompilers.bluebank.exceptions;

import java.util.NoSuchElementException;

public class AccountNotFoundException extends NoSuchElementException {
	
	public AccountNotFoundException(String accountNumber) {
		super(String.format("A conta $s não foi encontrada!", accountNumber));
	}
}
