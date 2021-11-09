package com.br.panacademy.devcompilers.bluebank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.panacademy.devcompilers.bluebank.entity.Cliente;
import com.br.panacademy.devcompilers.bluebank.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	
	public List<Cliente> findAll(){ //TODO ADICIONAR EXCEPTION CASO NÃO EXISTA CLIENTES
		List<Cliente> clientes = clienteRepository.findAll();
		return clientes;
	}

}
