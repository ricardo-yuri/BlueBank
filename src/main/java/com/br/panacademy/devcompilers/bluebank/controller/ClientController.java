package com.br.panacademy.devcompilers.bluebank.controller;

import com.br.panacademy.devcompilers.bluebank.dto.AddressDTO;
import com.br.panacademy.devcompilers.bluebank.dto.ClientDTO;
import com.br.panacademy.devcompilers.bluebank.service.ClientService;
import com.br.panacademy.devcompilers.bluebank.utils.DateFormatted;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/api/client")
@Api("Endpoint Client.")
public class ClientController {

	private static final Logger logger = LogManager.getLogger(ClientController.class);

	@Autowired
	ClientService clientService;

	@PostMapping
	@ApiOperation("Cria um novo cliente.")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Adiciona o cliente com sucesso."),
			@ApiResponse(code = 400, message = "Falha ao criar um novo cliente."),
	})

	public ResponseEntity createClient(@RequestBody @Valid ClientDTO clientDTO) {
		logger.trace(String.format("%s Log POST (create Client)", DateFormatted.currentDateFormattedPtBr()));
		return ResponseEntity.status(HttpStatus.CREATED).body(clientService.createClient(clientDTO));
	}

	@GetMapping("/getAll")
	@ApiOperation("Lista todos os clientes.")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de clientes com sucesso."),
            @ApiResponse(code = 400, message = "Falha ao listar clientes."),
    })
	public ResponseEntity findAll() {
		logger.trace(String.format("%s Log GET (findAll)", DateFormatted.currentDateFormattedPtBr()));

		List<ClientDTO> clients = clientService.findAllClient();
		return ResponseEntity.status(200).body(clients);
	}
	
	@GetMapping("/{id}")
	@ApiOperation("Retorna o clinte por ID.")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o cliente por ID com sucesso."),
            @ApiResponse(code = 400, message = "Falha ao buscar o cliente por ID."),
    })
	public ResponseEntity findById(@PathVariable Long id) {
		logger.trace(String.format("%s Log GET (findById)", DateFormatted.currentDateFormattedPtBr()));
		ClientDTO client = clientService.findByIdClient(id);

		return ResponseEntity.status(HttpStatus.OK).body(client);
	}

	@PutMapping("/update/{id}")
	@ApiOperation("Atualiza o cliente.")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atualiza o cliente com sucesso."),
            @ApiResponse(code = 400, message = "Falha ao atualizar o cliente."),
    })
	public ResponseEntity updateClient(@RequestBody @Valid ClientDTO clientDTO, @PathVariable Long id) {
		logger.trace(String.format("%s Log PUT (updateClient)", DateFormatted.currentDateFormattedPtBr()));
		ClientDTO client = clientService.updateClient(clientDTO);

		return ResponseEntity.status(HttpStatus.OK).body(client);
	}

	@DeleteMapping("/delete/{id}")
	@ApiOperation("Deleta um cliente.")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deleta o cliente com sucesso."),
            @ApiResponse(code = 400, message = "Falha ao deletar um cliente."),
    })
	public ResponseEntity<String> deleteClient(@PathVariable Long id) {
		logger.trace(String.format("%s Log DELETE (deleteClient)", DateFormatted.currentDateFormattedPtBr()));

		return ResponseEntity.status(HttpStatus.OK).body(clientService.deleteClient(id));
	}
	
	@GetMapping("/cep/{cep}")
	public ResponseEntity<AddressDTO> findAddressByCep(@PathVariable Integer cep) {
		logger.trace(String.format("%s Log GET CEP (findAddressByCep)", DateFormatted.currentDateFormattedPtBr()));
		return ResponseEntity.status(HttpStatus.OK).body(clientService.findAddressByCep(cep));
	}
	
}
