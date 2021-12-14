package com.br.panacademy.devcompilers.bluebank.controller;


import com.br.panacademy.devcompilers.bluebank.dto.AccountDTO;
import com.br.panacademy.devcompilers.bluebank.service.AccountService;
import com.br.panacademy.devcompilers.bluebank.utils.DateFormatted;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping(value = "/api/account")
@Api("Endpoint Account.")
public class AccountController {

    private static final Logger logger = LogManager.getLogger(ClientController.class);

    @Autowired
    private AccountService accountService;

    @PostMapping
    @ApiOperation("Cria uma nova conta.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cria a conta com Sucesso."),
            @ApiResponse(code = 400, message = "Falha ao criar a conta."),
    })
    public ResponseEntity<AccountDTO> createAccount(@RequestBody @Valid AccountDTO accountDTO) {
        logger.trace(String.format("%s Log POST (createAccount)", DateFormatted.currentDateFormattedPtBr()));
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.createAccount(accountDTO));
    }

    @GetMapping("/findById/{id}")
    @ApiOperation("Retorna uma conta por ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a conta por ID com Sucesso."),
            @ApiResponse(code = 400, message = "Falha ao buscar a conta por ID."),
    })
    public ResponseEntity<Object> findByIdAccount(@PathVariable Long id) {
        logger.trace(String.format("%s Log GET (findByIdAccount)", DateFormatted.currentDateFormattedPtBr()));
        return ResponseEntity.status(HttpStatus.OK).body(accountService.findByIdAccount(id));
    }

    @PutMapping("/update")
    @ApiOperation("Atualiza uma conta.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atualiza a conta com Sucesso."),
            @ApiResponse(code = 400, message = "Falha ao atualizar a conta."),
    })
    public ResponseEntity updateAccount(@RequestBody @Valid AccountDTO accountDTO) {
        logger.trace(String.format("%s Log PUT (updateAccount)", DateFormatted.currentDateFormattedPtBr()));

        AccountDTO accountToUpdate = accountService.updateAccount(accountDTO);
        return ResponseEntity.status(HttpStatus.OK).body(accountToUpdate);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Deleta uma conta.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Deleta a conta com Sucesso."),
            @ApiResponse(code = 400, message = "Falha ao deletar a conta."),
    })
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        logger.trace(String.format("%s Log DELETE (deleteAccount)", DateFormatted.currentDateFormattedPtBr()));

        return ResponseEntity.status(HttpStatus.OK).body(accountService.deleteAccount(id));
    }

    @PutMapping("/withdraw/{idAccount}/{value}")
    public ResponseEntity<String> withdrawAccount(@PathVariable Long idAccount, @PathVariable double value) {
        logger.trace(String.format("%s Log PUT (withdrawAccount)", DateFormatted.currentDateFormattedPtBr()));

        return ResponseEntity.status(HttpStatus.OK).body(accountService.withdrawAccount(idAccount, value));
    }

    @PutMapping("/deposit/{idAccount}/{value}")
    public ResponseEntity<String> depositAccount(@PathVariable Long idAccount, @PathVariable double value) {
        logger.trace(String.format("%s Log PUT (depositAccount)", DateFormatted.currentDateFormattedPtBr()));

        return ResponseEntity.status(HttpStatus.OK).body(accountService.depositAccount(idAccount, value));
    }

    @PutMapping("/transfer")
    public ResponseEntity transferAccount(
            @RequestParam(value = "idOriginAccount") Long idOriginAccount,
            @RequestParam(value = "idDestinationAccount") Long idDestinationAccount,
            @RequestParam(value = "value") double value) {
        logger.trace(String.format("%s Log PUT (transferAccount)", DateFormatted.currentDateFormattedPtBr()));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountService.transferAccount(idOriginAccount, idDestinationAccount, value));
    }

    @GetMapping("/historic/list/{id}")
    @ApiOperation("Retorna o histórico de movimentações da conta.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna todo o histórico da conta com Sucesso."),
            @ApiResponse(code = 400, message = "Falha ao listar o histórico da conta."),
    })
    public ResponseEntity listHistoric(@PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(accountService.listHistoricIdAccount(id));
    }

    @GetMapping("/historic")
    @ApiOperation("Retorna todo o histórico por Id da conta e a data da operação.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o histórico da conta por Id com Sucesso."),
            @ApiResponse(code = 400, message = "Falha ao retornar o histórico por Id da conta."),
    })

    public ResponseEntity findByDateHistoric(
            @RequestParam(name = "idAccount") Long idAccount,
            @RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate operationDate) {

        return ResponseEntity.status(HttpStatus.OK).body(accountService.findByDateHistoric(idAccount, operationDate));
    }
}
