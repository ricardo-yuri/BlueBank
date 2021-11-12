package com.br.panacademy.devcompilers.bluebank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.panacademy.devcompilers.bluebank.dto.ClienteDTO;
import com.br.panacademy.devcompilers.bluebank.dto.EnderecoDTO;
import com.br.panacademy.devcompilers.bluebank.service.ClienteService;

@RestController
@RequestMapping(value = "/api/clientes")
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<ClienteDTO> clientes = clienteService.findAll();
		return ResponseEntity.status(200).body(clientes);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) {
		ClienteDTO cliente = clienteService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(cliente);
	}
	
	@PostMapping
	public ResponseEntity<ClienteDTO> createCliente(@RequestBody ClienteDTO clienteDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.create(clienteDTO));
	}
	
	@PutMapping("/update")
	public ResponseEntity<ClienteDTO> updateCliente(@RequestBody ClienteDTO clienteDTO, @PathVariable Long id) {
		ClienteDTO cliente = clienteService.updateCliente(clienteDTO);
		return ResponseEntity.status(HttpStatus.OK).body(cliente);
	}	

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCliente(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.deleteCliente(id));
	}
	
	@GetMapping("cep/{cep}")
	public ResponseEntity<EnderecoDTO> findEnderecoByCep(@PathVariable Integer cep) {
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.findEnderecoByCep(cep));
	}
	
}
