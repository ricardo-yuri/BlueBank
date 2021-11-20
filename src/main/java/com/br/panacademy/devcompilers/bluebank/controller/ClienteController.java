package com.br.panacademy.devcompilers.bluebank.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.br.panacademy.devcompilers.bluebank.utils.DateUtil;
import lombok.extern.log4j.Log4j2;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@Log4j2
@RestController
@RequestMapping(value = "/api/clientes")
@Api("Endpoint Cliente.")
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;
	@Autowired
	DateUtil dateUtil;

	@GetMapping
	@ApiOperation("Lista todos os clientes.")
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = "Retorna a lista de clientes com sucesso."),
            @ApiResponse(code = 400, message = "Falha ao listar clientes."),
    })
	public ResponseEntity<List<ClienteDTO>> findAll() {
		log.info(dateUtil.dateFormatted(LocalDateTime.now()).concat(" Log GET (findAll)"));
		List<ClienteDTO> clientes = clienteService.findAll();
		return ResponseEntity.status(201).body(clientes);
	}
	
	@GetMapping("/{id}")
	@ApiOperation("Retorna o clinte por ID.")
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = "Retorna o cliente por ID com Sucesso."),
            @ApiResponse(code = 400, message = "Falha ao buscar o cliente por ID."),
    })
	public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) {
		log.info(dateUtil.dateFormatted(LocalDateTime.now()).concat(" Log GET (findById)"));
		ClienteDTO cliente = clienteService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(cliente);
	}
	
	@PostMapping
	@ApiOperation("Cria um novo cliente.")
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = "Adiciona o cliente com Sucesso."),
            @ApiResponse(code = 400, message = "Falha ao criar um novo cliente."),
    })
	public ResponseEntity<ClienteDTO> createCliente(@RequestBody ClienteDTO clienteDTO) {
		log.info(dateUtil.dateFormatted(LocalDateTime.now()).concat(" Log POST (createCliente)"));
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.create(clienteDTO));
	}
	
	@PutMapping("/update")
	@ApiOperation("Atualiza o cliente.")
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = "Atualiza o cliente com Sucesso."),
            @ApiResponse(code = 400, message = "Falha ao atualizar o cliente."),
    })
	public ResponseEntity<ClienteDTO> updateCliente(@RequestBody ClienteDTO clienteDTO, @PathVariable Long id) {
		log.info(dateUtil.dateFormatted(LocalDateTime.now()).concat(" Log PUT (updateCliente)"));
		ClienteDTO cliente = clienteService.updateCliente(clienteDTO);
		return ResponseEntity.status(HttpStatus.OK).body(cliente);
	}	

	@DeleteMapping("/delete/{id}")
	@ApiOperation("Deleta um cliente.")
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = "Sucesso ao deletar o cliente."),
            @ApiResponse(code = 400, message = "Falha ao deletar um cliente."),
    })
	public ResponseEntity<String> deleteCliente(@PathVariable Long id) {
		log.info(dateUtil.dateFormatted(LocalDateTime.now()).concat(" Log DELETE (deleteCliente)"));
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.deleteCliente(id));
	}
	
	@GetMapping("cep/{cep}")
	public ResponseEntity<EnderecoDTO> findEnderecoByCep(@PathVariable Integer cep) {
		log.info(dateUtil.dateFormatted(LocalDateTime.now()).concat(" Log GET CEP (findEnderecoByCep)"));
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.findEnderecoByCep(cep));
	}
	
}
