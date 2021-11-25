package com.br.panacademy.devcompilers.bluebank.controller;


import java.util.NoSuchElementException;


import com.br.panacademy.devcompilers.bluebank.utils.DateUtil;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.panacademy.devcompilers.bluebank.dto.ContaDTO;
import com.br.panacademy.devcompilers.bluebank.service.ContaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Log4j2
@RestController
@RequestMapping(value = "/api/contas")
@Api("Endpoint Conta.")
public class ContaController {

	@Autowired
	private ContaService contaService;
	@Autowired
	private DateUtil dateUtil;
	
	@PostMapping
	@ApiOperation("Cria uma nova conta.")
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cria a conta com Sucesso."),
            @ApiResponse(code = 400, message = "Falha ao criar a conta."),
    })
	public ResponseEntity<ContaDTO> createConta(@RequestBody ContaDTO contaDTO) {
		log.info(dateUtil.dateFormatted(LocalDateTime.now()).concat(" Log POST (createConta)"));
		return ResponseEntity.status(HttpStatus.CREATED).body(contaService.createConta(contaDTO));	
	}
	
	@GetMapping("/findById/{id}")
	@ApiOperation("Retorna uma conta por ID.")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a conta por ID com Sucesso."),
            @ApiResponse(code = 400, message = "Falha ao buscar a conta por ID."),
    })
	public ResponseEntity<Object> findByIdConta(@PathVariable Long id) {
		log.info(dateUtil.dateFormatted(LocalDateTime.now()).concat(" Log GET (findByIdConta)"));
		try {
            return ResponseEntity.status(HttpStatus.OK).body(contaService.findByIdConta(id));
        }catch (NoSuchElementException err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getMessage());
        }
	}
	
	@PutMapping("/update")
	@ApiOperation("Atualiza uma conta.")
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = "Atualiza a conta com Sucesso."),
            @ApiResponse(code = 400, message = "Falha ao atualizar a conta."),
    })
	public ResponseEntity<ContaDTO> updateConta(@RequestBody ContaDTO contaDTO) {
		log.info(dateUtil.dateFormatted(LocalDateTime.now()).concat(" Log PUT (updateConta)"));
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
		log.info(dateUtil.dateFormatted(LocalDateTime.now()).concat(" Log DELETE (deleteConta)"));
		return ResponseEntity.status(HttpStatus.OK).body(contaService.deleteConta(id));
	}
	
	@PutMapping("/sacar/{idConta}/{valor}")
	public ResponseEntity<String> sacarConta(@PathVariable Long idConta, @PathVariable double valor) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(contaService.sacarConta(idConta, valor));
		}catch (Exception err) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(contaService.sacarConta(idConta, valor));
		}
	}
	
	@PutMapping("/depositar/{idConta}/{valor}")
	public ResponseEntity<String> depositarConta(@PathVariable Long idConta, @PathVariable double valor) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(contaService.depositaConta(idConta, valor));
		}catch (Exception err) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(contaService.depositaConta(idConta, valor));
		}
	}
	
	@PutMapping("/tranferir/{valor}")
	public ResponseEntity<String> transfereConta(
			@RequestParam(value = "idContaOrigem") Long idContaOrigem,
			@RequestParam(value = "idContaDestino") Long idContaDestino,
			@PathVariable double valor) {

		try {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(contaService.transfereConta(idContaOrigem, idContaDestino, valor));
		}catch (Exception err) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(contaService.transfereConta(idContaOrigem, idContaDestino, valor));
		}
	}
}
