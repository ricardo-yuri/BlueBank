package com.br.panacademy.devcompilers.bluebank.controller;

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

import com.br.panacademy.devcompilers.bluebank.dto.ContaDTO;
import com.br.panacademy.devcompilers.bluebank.service.ContaService;

@RestController
@RequestMapping(value = "/api/contas")
public class ContaController {

	@Autowired
	private ContaService contaService; 
	
	@PostMapping
	public ResponseEntity<ContaDTO> createConta(@RequestBody ContaDTO contaDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(contaService.createConta(contaDTO));	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ContaDTO> findByIdConta(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(contaService.findByIdConta(id));
	}
	
	@PutMapping("/update")
	public ResponseEntity<ContaDTO> updateConta(@RequestBody ContaDTO contaDTO) {
		ContaDTO contaToUpdate = contaService.updateConta(contaDTO);
		return ResponseEntity.status(HttpStatus.OK).body(contaToUpdate);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteConta(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(contaService.deleteConta(id));	
	}
}
