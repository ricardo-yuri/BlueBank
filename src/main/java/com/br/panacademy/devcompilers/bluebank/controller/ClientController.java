package com.br.panacademy.devcompilers.bluebank.controller;

import com.br.panacademy.devcompilers.bluebank.dto.AddressDTO;
import com.br.panacademy.devcompilers.bluebank.dto.ClientDTO;
import com.br.panacademy.devcompilers.bluebank.service.ClientService;
import com.br.panacademy.devcompilers.bluebank.utils.DateFormatted;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
@RequestMapping(value = "/api/client")
@Api("Endpoint Client.")
public class ClientController {
	
	@Autowired
	ClientService clientService;

	@PostMapping
	@ApiOperation("Cria um novo cliente.")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Adiciona o cliente com sucesso."),
			@ApiResponse(code = 400, message = "Falha ao criar um novo cliente."),
	})

	public ResponseEntity createClient(@RequestBody @Valid ClientDTO clientDTO) {
		log.info(DateFormatted.currentDateFormattedPtBr().concat(" Log POST (create Client)"));
		return ResponseEntity.status(HttpStatus.CREATED).body(clientService.createClient(clientDTO));
	}

	@GetMapping("/getAll")
	@ApiOperation("Lista todos os clientes.")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de clientes com sucesso."),
            @ApiResponse(code = 400, message = "Falha ao listar clientes."),
    })
	public ResponseEntity findAll() {
		log.info(DateFormatted.currentDateFormattedPtBr().concat(" Log GET (findAll)"));

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
		log.info(DateFormatted.currentDateFormattedPtBr().concat(" Log GET (findById)"));
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
		log.info(DateFormatted.currentDateFormattedPtBr().concat(" Log PUT (updateClient)"));
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
		log.info(DateFormatted.currentDateFormattedPtBr().concat(" Log DELETE (deleteClient)"));

		return ResponseEntity.status(HttpStatus.OK).body(clientService.deleteClient(id));
	}
	
	@GetMapping("/cep/{cep}")
	public ResponseEntity<AddressDTO> findAddressByCep(@PathVariable Integer cep) {
		log.info(DateFormatted.currentDateFormattedPtBr().concat(" Log GET CEP (findAddressByCep)"));
		return ResponseEntity.status(HttpStatus.OK).body(clientService.findAddressByCep(cep));
	}
	
}
