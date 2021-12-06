package com.br.panacademy.devcompilers.bluebank.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import com.br.panacademy.devcompilers.bluebank.exceptions.OperationIllegalException;
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

import com.br.panacademy.devcompilers.bluebank.dto.ClientDTO;
import com.br.panacademy.devcompilers.bluebank.dto.AddressDTO;
import com.br.panacademy.devcompilers.bluebank.service.ClientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@Log4j2
@RestController
@RequestMapping(value = "/api/client")
@Api("Endpoint Client.")
public class ClientController {
	
	@Autowired
	ClientService clientService;
	@Autowired
	DateUtil dateUtil;

	@GetMapping("/getAll")
	@ApiOperation("Lista todos os clientes.")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de clientes com sucesso."),
            @ApiResponse(code = 400, message = "Falha ao listar clientes."),
    })
	public ResponseEntity findAll() {
		log.info(dateUtil.dateFormatted(LocalDateTime.now()).concat(" Log GET (findAll)"));
		try {
			List<ClientDTO> clients = clientService.findAllClient();
			return ResponseEntity.status(200).body(clients);
		} catch(NoSuchElementException err) {
			return ResponseEntity.status(400).body(err.getMessage());
		}
	}
	
	@GetMapping("/{id}")
	@ApiOperation("Retorna o clinte por ID.")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o cliente por ID com sucesso."),
            @ApiResponse(code = 400, message = "Falha ao buscar o cliente por ID."),
    })
	public ResponseEntity findById(@PathVariable Long id) {
		log.info(dateUtil.dateFormatted(LocalDateTime.now()).concat(" Log GET (findById)"));
		ClientDTO client = clientService.findByIdClient(id);
		try {
			return ResponseEntity.status(HttpStatus.OK).body(client);
		} catch(NoSuchElementException err) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getMessage());
		}
	}
	
	@PostMapping
	@ApiOperation("Cria um novo cliente.")
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = "Adiciona o cliente com sucesso."),
            @ApiResponse(code = 400, message = "Falha ao criar um novo cliente."),
    })
	public ResponseEntity createClient(@RequestBody ClientDTO clientDTO) {
		log.info(dateUtil.dateFormatted(LocalDateTime.now()).concat(" Log POST (createClient)"));
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(clientService.createClient(clientDTO));
		} catch(OperationIllegalException err) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getMessage());
		}
	}
	
	@PutMapping("/update/{id}")
	@ApiOperation("Atualiza o cliente.")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atualiza o cliente com sucesso."),
            @ApiResponse(code = 400, message = "Falha ao atualizar o cliente."),
    })
	public ResponseEntity updateClient(@RequestBody ClientDTO clientDTO, @PathVariable Long id) {
		log.info(dateUtil.dateFormatted(LocalDateTime.now()).concat(" Log PUT (updateClient)"));
		ClientDTO client = clientService.updateClient(clientDTO);
		try {
			return ResponseEntity.status(HttpStatus.OK).body(client);
		} catch(NoSuchElementException err) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getMessage());
		}
	}

	@DeleteMapping("/delete/{id}")
	@ApiOperation("Deleta um cliente.")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deleta o cliente com sucesso."),
            @ApiResponse(code = 400, message = "Falha ao deletar um cliente."),
    })
	public ResponseEntity<String> deleteClient(@PathVariable Long id) {
		log.info(dateUtil.dateFormatted(LocalDateTime.now()).concat(" Log DELETE (deleteClient)"));
		try {
			return ResponseEntity.status(HttpStatus.OK).body(clientService.deleteClient(id));
		} catch(NoSuchElementException err) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getMessage());
		}
	}
	
	@GetMapping("/cep/{cep}")
	public ResponseEntity<AddressDTO> findAddressByCep(@PathVariable Integer cep) {
		log.info(dateUtil.dateFormatted(LocalDateTime.now()).concat(" Log GET CEP (findAddressByCep)"));
		return ResponseEntity.status(HttpStatus.OK).body(clientService.findAddressByCep(cep));
	}
	
}
