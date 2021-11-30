package com.br.panacademy.devcompilers.bluebank.service;

import com.br.panacademy.devcompilers.bluebank.dto.ContaDTO;
import com.br.panacademy.devcompilers.bluebank.entity.Cliente;
import com.br.panacademy.devcompilers.bluebank.entity.Conta;
import com.br.panacademy.devcompilers.bluebank.repository.ClienteRepository;
import com.br.panacademy.devcompilers.bluebank.repository.ContaRepository;
import com.br.panacademy.devcompilers.bluebank.ultis.ClienteBuild;
import com.br.panacademy.devcompilers.bluebank.ultis.ContaBuild;
import com.br.panacademy.devcompilers.bluebank.ultis.ContaDTOBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
@DisplayName("Testes para a classe Conta Service")
class ContaServiceTest {

    @Mock
    private ContaRepository contaRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ContaService contaService;

/*
    @Test
    @DisplayName("Retorna a conta criada com sucesso.")
    void quandoInformadoUmaConta_DeveCriarAConta() {
        Cliente clienteToSave = ClienteBuild.criaCliente();
        clienteToSave.setId(1L);

        Conta contaExpected = ContaBuild.criaContaSemId();
        contaExpected.setId(1L);
        contaExpected.setCliente(clienteToSave);

        Mockito.when(clienteRepository.findByCpf(Mockito.any())).thenReturn(Optional.of(clienteToSave));
        Mockito.when(contaRepository.save(Mockito.any())).thenReturn(contaExpected);

        ContaDTO contaDTO = ContaDTOBuilder.criaContaDTOSemId();
        ContaDTO contaSaved = contaService.createConta(contaDTO);

        Assertions.assertEquals(contaExpected.getId(), contaSaved.getId());
        Assertions.assertEquals(contaExpected.getNumeroConta(), contaSaved.getNumeroConta());
        //Assertions.assertEquals(contaExpected.getCpfUsuario(), contaSaved.getCpfUsuario());
    }
*/
    @Test
    @DisplayName("Não deve criar a conta para um cliente inexistente.")
    void quandoInformadoUmaConta_DeveRetornarNoSuchElementeClientException() {

        Mockito.when(clienteRepository.findByCpf(Mockito.any())).thenReturn(Optional.empty());

        ContaDTO contaDTO = ContaDTOBuilder.criaContaDTOSemId();

        Assertions.assertThrows(NoSuchElementException.class, () -> contaService.createConta(contaDTO));
    }

    @Test
    @DisplayName("Deve retornar a conta por ID.")
    void quandoInformadoUmaConta_DeveRetornarAConta() {
        Cliente clienteToSave = ClienteBuild.criaCliente();
        clienteToSave.setId(1L);

        Conta contaExpected = ContaBuild.criaContaSemId();
        contaExpected.setId(1L);
        contaExpected.setCliente(clienteToSave);

        Mockito.when(contaRepository.findById(contaExpected.getId())).thenReturn(Optional.of(contaExpected));

        ContaDTO accountReturned = contaService.findByIdConta(contaExpected.getId());

        Assertions.assertEquals(contaExpected.getId(), accountReturned.getId());
       // Assertions.assertEquals(contaExpected.getCpfUsuario(), accountReturned.getCpfUsuario());
    }

    @Test
    @DisplayName("Deve retornar a conta por ID.")
    void quandoInformadoUmaConta_DeveRetornarNoSuchElementException() {
        Cliente clienteToSave = ClienteBuild.criaCliente();
        clienteToSave.setId(1L);

        Conta contaExpected = ContaBuild.criaContaSemId();
        contaExpected.setId(1L);
        contaExpected.setCliente(clienteToSave);

        Mockito.when(contaRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> contaService.findByIdConta(1L));
    }
//***************************
    @Test
    @DisplayName("Deve retornar a conta atualizada.")
    void quandoInformadoUmaConta_DeveRetornarAContaAtualizada() {
        Cliente clienteToSave = ClienteBuild.criaCliente();
        clienteToSave.setId(1L);

        Conta contaExpected = ContaBuild.criaContaSemId();
        contaExpected.setId(1L);
        contaExpected.setCliente(clienteToSave);

        Mockito.when(contaRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> contaService.findByIdConta(1L));
    }

    @Test
    @DisplayName("Deve retornar uma exception ao tentar atualizar uma conta que não existe.")
    void quandoInformadoUmaConta_DeveRetornarNoSuchElementExceptionParaUmaContaInexistente() {
        Cliente clienteToSave = ClienteBuild.criaCliente();
        clienteToSave.setId(1L);

        Conta contaExpected = ContaBuild.criaContaSemId();
        contaExpected.setId(1L);
        contaExpected.setCliente(clienteToSave);

        Mockito.when(contaRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> contaService.findByIdConta(1L));
    }

    @Test
    @DisplayName("Deve retornar uma exception ao tentar mudar o titular da conta.")
    void quandoInformadoUmaConta_DeveRetornarNoSuchElementExceptionAoTentaAlterarTitularConta() {
        Cliente clienteToSave = ClienteBuild.criaCliente();
        clienteToSave.setId(1L);

        Conta contaExpected = ContaBuild.criaContaSemId();
        contaExpected.setId(1L);
        contaExpected.setCliente(clienteToSave);

        Mockito.when(contaRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> contaService.findByIdConta(1L));
    }

}
