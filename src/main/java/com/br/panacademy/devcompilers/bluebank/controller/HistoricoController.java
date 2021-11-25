package com.br.panacademy.devcompilers.bluebank.controller;

import com.br.panacademy.devcompilers.bluebank.entity.Historico;
import com.br.panacademy.devcompilers.bluebank.service.HistoricoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/historico")
public class HistoricoController {

    @Autowired
    private HistoricoService historicoService;

    @GetMapping("/{id}")
    @ApiOperation("Retorna um histórico por ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o histórico por ID com Sucesso."),
            @ApiResponse(code = 400, message = "Falha ao buscar o histórico por ID."),
    })
    public ResponseEntity findByIdHistorico(@PathVariable Long id) {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(historicoService.findByIdLog(id));
        }catch (NoSuchElementException err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getMessage());
        }
    }

    @GetMapping("/findAll")
    @ApiOperation("Retorna todos os logs.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna todos os logs com Sucesso."),
            @ApiResponse(code = 400, message = "Falha ao retornar todos os logs."),
    })
    public ResponseEntity<Object> findByIdHistorico() {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(historicoService.findAllLog());
        }catch (NoSuchElementException err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getMessage());
        }
    }

    @GetMapping("/list/{id}")
    @ApiOperation("Retorna todo o histórico por Id da conta.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna todo o histórico do da conta com Sucesso."),
            @ApiResponse(code = 400, message = "Falha ao retornar o histórico da conta."),
    })
    public ResponseEntity listIdConta(@PathVariable Long id) {
        try {
            List<Historico> list = historicoService.listIdConta(id);
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }catch (NoSuchElementException err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getMessage());
        }
    }
}
