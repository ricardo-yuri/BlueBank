package com.br.panacademy.devcompilers.bluebank.controller;


import com.br.panacademy.devcompilers.bluebank.dto.AccountDTO;
import com.br.panacademy.devcompilers.bluebank.exceptions.AccountNotFoundException;
import com.br.panacademy.devcompilers.bluebank.exceptions.OperationIllegalException;
import com.br.panacademy.devcompilers.bluebank.service.AccountService;
import com.br.panacademy.devcompilers.bluebank.utils.DateUtil;
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
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Log4j2
@RestController
@RequestMapping(value = "/api/account")
@Api("Endpoint Account.")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private DateUtil dateUtil;

    @PostMapping
    @ApiOperation("Cria uma nova conta.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cria a conta com Sucesso."),
            @ApiResponse(code = 400, message = "Falha ao criar a conta."),
    })
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO) {
        log.info(dateUtil.dateFormatted(LocalDateTime.now()).concat(" Log POST (createAccount)"));
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.createAccount(accountDTO));
    }

    @GetMapping("/findById/{id}")
    @ApiOperation("Retorna uma conta por ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a conta por ID com Sucesso."),
            @ApiResponse(code = 400, message = "Falha ao buscar a conta por ID."),
    })
    public ResponseEntity<Object> findByIdAccount(@PathVariable Long id) {
        log.info(dateUtil.dateFormatted(LocalDateTime.now()).concat(" Log GET (findByIdAccount)"));
        try {
            return ResponseEntity.status(HttpStatus.OK).body(accountService.findByIdAccount(id));
        } catch (AccountNotFoundException err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getMessage());
        }
    }

    @PutMapping("/update")
    @ApiOperation("Atualiza uma conta.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Atualiza a conta com Sucesso."),
            @ApiResponse(code = 400, message = "Falha ao atualizar a conta."),
    })
    public ResponseEntity updateAccount(@RequestBody AccountDTO accountDTO) {
        log.info(dateUtil.dateFormatted(LocalDateTime.now()).concat(" Log PUT (updateAccount)"));
        try {
            AccountDTO accountToUpdate = accountService.updateAccount(accountDTO);
            return ResponseEntity.status(HttpStatus.OK).body(accountToUpdate);
        } catch (AccountNotFoundException | OperationIllegalException err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Deleta uma conta.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Deleta a conta com Sucesso."),
            @ApiResponse(code = 400, message = "Falha ao deletar a conta."),
    })
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        log.info(dateUtil.dateFormatted(LocalDateTime.now()).concat(" Log DELETE (deleteAccount)"));
        try {
            return ResponseEntity.status(HttpStatus.OK).body(accountService.deleteAccount(id));
        } catch (AccountNotFoundException | OperationIllegalException err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getMessage());
        }
    }

    @PutMapping("/withdraw/{idAccount}/{value}")
    public ResponseEntity<String> withdrawAccount(@PathVariable Long idAccount, @PathVariable double value) {
        log.info(dateUtil.dateFormatted(LocalDateTime.now()).concat(" Log PUT (withdrawAccount)"));
        try {
            return ResponseEntity.status(HttpStatus.OK).body(accountService.withdrawAccount(idAccount, value));
        } catch (AccountNotFoundException | OperationIllegalException err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getMessage());
        }
    }

    @PutMapping("/deposit/{idAccount}/{value}")
    public ResponseEntity<String> depositAccount(@PathVariable Long idAccount, @PathVariable double value) {
        log.info(dateUtil.dateFormatted(LocalDateTime.now()).concat(" Log PUT (depositAccount)"));
        try {
            return ResponseEntity.status(HttpStatus.OK).body(accountService.depositAccount(idAccount, value));
        } catch (AccountNotFoundException | OperationIllegalException err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getMessage());
        }
    }

    @PutMapping("/transfer")
    public ResponseEntity transferAccount(
            @RequestParam(value = "idOriginAccount") Long idOriginAccount,
            @RequestParam(value = "idDestinationAccount") Long idDestinationAccount,
            @RequestParam(value = "value") double value) {
        log.info(dateUtil.dateFormatted(LocalDateTime.now()).concat(" Log PUT (transferAccount)"));
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(accountService.transferAccount(idOriginAccount, idDestinationAccount, value));
        } catch (AccountNotFoundException | OperationIllegalException err) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(err.getMessage());
        }
    }

    @GetMapping("/historic/list/{id}")
    @ApiOperation("Retorna o histórico de movimentações da conta.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna todo o histórico da conta com Sucesso."),
            @ApiResponse(code = 400, message = "Falha ao listar o histórico da conta."),
    })
    public ResponseEntity listHistoric(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(accountService.listHistoricIdAccount(id));
        } catch(NoSuchElementException err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getMessage());
        }
    }

    @GetMapping("/historic/{id}")
    @ApiOperation("Retorna todo o histórico por Id da conta.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o histórico da conta por Id com Sucesso."),
            @ApiResponse(code = 400, message = "Falha ao retornar o histórico por Id da conta."),
    })
    public ResponseEntity findByIdAccountHistoric(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(accountService.findByIdAccountHistoric(id));
        } catch(NoSuchElementException err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getMessage());
        }
    }
}
