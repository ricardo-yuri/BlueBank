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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/contas")
@Api("Endpoint Conta.")
public class ContaController {

	@Autowired
	private ContaService contaService; 
	
	@PostMapping
	@ApiOperation("Cria uma nova conta.")
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cria a conta com Sucesso."),
            @ApiResponse(code = 400, message = "Falha ao criar a conta."),
    })
	public ResponseEntity<ContaDTO> createConta(@RequestBody ContaDTO contaDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(contaService.createConta(contaDTO));	
	}
	
	@GetMapping("/{id}")
	@ApiOperation("Retorna uma conta por ID.")
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = "Retorna a conta por ID com Sucesso."),
            @ApiResponse(code = 400, message = "Falha ao buscar a conta por ID."),
    })
	public ResponseEntity<ContaDTO> findByIdConta(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(contaService.findByIdConta(id));
	}
	
	@PutMapping("/update")
	@ApiOperation("Atualiza uma conta.")
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = "Atualiza a conta com Sucesso."),
            @ApiResponse(code = 400, message = "Falha ao atualizar a conta."),
    })
	public ResponseEntity<ContaDTO> updateConta(@RequestBody ContaDTO contaDTO) {
		ContaDTO contaToUpdate = contaService.updateConta(contaDTO);
		return ResponseEntity.status(HttpStatus.OK).body(contaToUpdate);
	}
	
	@DeleteMapping("/delete/{id}")
	@ApiOperation("Deleta uma conta.")
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = "Deleta a conta com Sucesso."),
            @ApiResponse(code = 400, message = "Falha ao deletar a conta."),
    })
	public ResponseEntity<String> deleteConta(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(contaService.deleteConta(id));	
	}
}
