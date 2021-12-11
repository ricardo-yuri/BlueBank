package com.br.panacademy.devcompilers.bluebank.controller;


import com.br.panacademy.devcompilers.bluebank.dto.AccountDTO;
import com.br.panacademy.devcompilers.bluebank.service.AccountService;
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

@Log4j2
@RestController
@RequestMapping(value = "/api/account")
@Api("Endpoint Account.")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    @ApiOperation("Cria uma nova conta.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cria a conta com Sucesso."),
            @ApiResponse(code = 400, message = "Falha ao criar a conta."),
    })
    public ResponseEntity<AccountDTO> createAccount(@RequestBody @Valid AccountDTO accountDTO) {
        log.info(DateFormatted.currentDateFormattedPtBr().concat(" Log POST (createAccount)"));
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.createAccount(accountDTO));
    }

    @GetMapping("/findById/{id}")
    @ApiOperation("Retorna uma conta por ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a conta por ID com Sucesso."),
            @ApiResponse(code = 400, message = "Falha ao buscar a conta por ID."),
    })
    public ResponseEntity<Object> findByIdAccount(@PathVariable Long id) {
        log.info(DateFormatted.currentDateFormattedPtBr().concat(" Log GET (findByIdAccount)"));
        return ResponseEntity.status(HttpStatus.OK).body(accountService.findByIdAccount(id));
    }

    @PutMapping("/update")
    @ApiOperation("Atualiza uma conta.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atualiza a conta com Sucesso."),
            @ApiResponse(code = 400, message = "Falha ao atualizar a conta."),
    })
    public ResponseEntity updateAccount(@RequestBody @Valid AccountDTO accountDTO) {
        log.info(DateFormatted.currentDateFormattedPtBr().concat(" Log PUT (updateAccount)"));

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
        log.info(DateFormatted.currentDateFormattedPtBr().concat(" Log DELETE (deleteAccount)"));

        return ResponseEntity.status(HttpStatus.OK).body(accountService.deleteAccount(id));
    }

    @PutMapping("/withdraw/{idAccount}/{value}")
    public ResponseEntity<String> withdrawAccount(@PathVariable Long idAccount, @PathVariable double value) {
        log.info(DateFormatted.currentDateFormattedPtBr().concat(" Log PUT (withdrawAccount)"));

        return ResponseEntity.status(HttpStatus.OK).body(accountService.withdrawAccount(idAccount, value));
    }

    @PutMapping("/deposit/{idAccount}/{value}")
    public ResponseEntity<String> depositAccount(@PathVariable Long idAccount, @PathVariable double value) {
        log.info(DateFormatted.currentDateFormattedPtBr().concat(" Log PUT (depositAccount)"));

        return ResponseEntity.status(HttpStatus.OK).body(accountService.depositAccount(idAccount, value));
    }

    @PutMapping("/transfer")
    public ResponseEntity transferAccount(
            @RequestParam(value = "idOriginAccount") Long idOriginAccount,
            @RequestParam(value = "idDestinationAccount") Long idDestinationAccount,
            @RequestParam(value = "value") double value) {
        log.info(DateFormatted.currentDateFormattedPtBr().concat(" Log PUT (transferAccount)"));

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
/*
    @GetMapping("/historic")
    @ApiOperation("Retorna todo o histórico por Id da conta.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o histórico da conta por Id com Sucesso."),
            @ApiResponse(code = 400, message = "Falha ao retornar o histórico por Id da conta."),
    })

    public ResponseEntity findByDateHistoric(
            @RequestParam(name = "idAccount") Long idAccount,
            @RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        System.out.println("Data: " + date);
        return ResponseEntity.status(HttpStatus.OK).body(accountService.findByDateHistoric(idAccount, date));
    }
*/
}
