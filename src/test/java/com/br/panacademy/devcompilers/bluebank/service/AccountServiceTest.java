package com.br.panacademy.devcompilers.bluebank.service;

import com.br.panacademy.devcompilers.bluebank.dto.AccountDTO;
import com.br.panacademy.devcompilers.bluebank.entity.Account;
import com.br.panacademy.devcompilers.bluebank.entity.Client;
import com.br.panacademy.devcompilers.bluebank.exceptions.OperationIllegalException;
import com.br.panacademy.devcompilers.bluebank.repository.AccountRepository;
import com.br.panacademy.devcompilers.bluebank.repository.ClientRepository;
import com.br.panacademy.devcompilers.bluebank.ultis.AccountBuilder;
import com.br.panacademy.devcompilers.bluebank.ultis.AccountDTOBuilder;
import com.br.panacademy.devcompilers.bluebank.ultis.ClientBuilder;
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
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private ClientRepository clientRepository;

    //@Mock
    //private HistoricService historicService;

    @InjectMocks
    private AccountService accountService;


    @Test
    @DisplayName("Retorna a conta criada com sucesso.")
    void quandoInformadoUmaConta_DeveCriarAConta() {

        Account contaEsperada = returnAccount(returnAccount());

        Mockito.when(clientRepository.findByCpf(Mockito.any())).thenReturn(Optional.of(returnAccount()));
        Mockito.when(accountRepository.save(Mockito.any())).thenReturn(contaEsperada);

        AccountDTO contaDTO = AccountDTOBuilder.createAccountDTO();
        AccountDTO contaSalva = accountService.createAccount(contaDTO);

        Assertions.assertEquals(contaEsperada.getId(), contaSalva.getId());
        Assertions.assertEquals(contaEsperada.getAccountNumber(), contaSalva.getAccountNumber());
        Assertions.assertEquals(contaEsperada.getClient().getCpf(), contaSalva.getCpfUser());
    }

    @Test
    @DisplayName("Não deve criar a conta para um cliente inexistente.")
    void quandoInformadoUmaConta_DeveRetornarNoSuchElementeClientException() {

        Mockito.when(clientRepository.findByCpf(Mockito.any())).thenReturn(Optional.empty());

        AccountDTO contaDTO = AccountDTOBuilder.createAccountDTO();

        Assertions.assertThrows(NoSuchElementException.class, () -> accountService.createAccount(contaDTO));
    }

    @Test
    @DisplayName("Deve retornar a conta por ID.")
    void quandoInformadoUmaConta_DeveRetornarAConta() {
        Account contaEsperada = returnAccount(returnAccount());

        Mockito.when(accountRepository.findByIdAndDeletedIsFalse(Mockito.any())).thenReturn(Optional.of(contaEsperada));

        AccountDTO contaRetornada = accountService.findByIdAccount(contaEsperada.getId());

        Assertions.assertEquals(contaEsperada.getId(), contaRetornada.getId());
        Assertions.assertEquals(contaEsperada.getClient().getCpf(), contaRetornada.getCpfUser());
    }

    @Test
    @DisplayName("Deve retornar Exception ao bucar uma conta por ID.")
    void quandoInformadoUmaConta_DeveRetornarNoSuchElementException() {

        Mockito.when(accountRepository.findByIdAndDeletedIsFalse(Mockito.any())).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> accountService.findByIdAccount(1L));
    }

    @Test
    @DisplayName("Deve retornar a conta deletada com sucesso.")
    void quandoInformadoUmaConta_DeveRetornarAContaDeletada() {

        Account contaSalva = returnAccount(returnAccount());
        contaSalva.setDeleted(true);

        Mockito.when(accountRepository.findByIdAndDeletedIsFalse(Mockito.any())).thenReturn(Optional.of(contaSalva));
        Mockito.when(accountRepository.save(Mockito.any())).thenReturn(contaSalva);

        String mensagemRetornada = accountService.deleteAccount(1L);

        Assertions.assertEquals(String.format("Conta com Id: %d deletada!", contaSalva.getId()), mensagemRetornada);
    }

    @Test
    @DisplayName("Deve retornar uma exception ao tentar deletar uma conta que não existe.")
    void quandoInformadoUmaConta_DeveRetornarNoSuchElementExceptionParaUmaContaInexistente() {

        Mockito.when(accountRepository.findByIdAndDeletedIsFalse(Mockito.any())).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> accountService.deleteAccount(1L));
    }

    @Test
    @DisplayName("Deve retornar uma exception ao tentar mudar o titular da conta.")
    void quandoInformadoUmaConta_DeveRetornarNoSuchElementExceptionAoTentaAlterarTitularConta() {

        Account contaEsperada = returnAccount(returnAccount());

        AccountDTO contaUpdate = AccountDTOBuilder.createAccountDTO();
        contaUpdate.setId(1L);
        contaUpdate.setCpfUser("000.000.000.00");

        Mockito.when(accountRepository.findByIdAndDeletedIsFalse(Mockito.any())).thenReturn(Optional.of(contaEsperada));

        Assertions.assertThrows(OperationIllegalException.class, () -> accountService.updateAccount(contaUpdate));
    }

    @Test
    @DisplayName("Deve retornar uma exception ao tentar mudar o número da conta.")
    void quandoInformadoUmaConta_DeveRetornarNoSuchElementExceptionAoTentaAlterarNumeroConta() {

        Account contaEsperada = returnAccount(returnAccount());

        AccountDTO contaUpdate = AccountDTOBuilder.createAccountDTO();
        contaUpdate.setId(1L);
        contaUpdate.setAccountNumber("0002-4");

        Mockito.when(accountRepository.findByIdAndDeletedIsFalse(Mockito.any())).thenReturn(Optional.of(contaEsperada));

        Assertions.assertThrows(OperationIllegalException.class, () -> accountService.updateAccount(contaUpdate));
    }

    @Test
    @DisplayName("Deve permitir sacar valor da conta.")
    void quandoInformadoUmValor_DeveRetornarSaqueEfetuadoComSucesso() {

        Account contaSalva = returnAccount(returnAccount());
        contaSalva.setAccountBalance(1000);

        Mockito.when(accountRepository.findByIdAndDeletedIsFalse(Mockito.any())).thenReturn(Optional.of(contaSalva));
        Mockito.when(accountRepository.save(contaSalva)).thenReturn(contaSalva);

        String mensagemRetorno = accountService.withdrawAccount(contaSalva.getId(), 900);

        Assertions.assertEquals("Saque realizado!", mensagemRetorno);
        Assertions.assertEquals(contaSalva.getAccountBalance(), 100);
    }

    @Test
    @DisplayName("Deve retornar uma exception ao tentar sacar um valor de uma conta inexistente.")
    void quandoInformadoUmValor_DeveRetornarContaNaoEncontrada() {

        Mockito.when(accountRepository.findByIdAndDeletedIsFalse(Mockito.any())).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> accountService.withdrawAccount(1L, 100));
    }

    @Test
    @DisplayName("Deve retornar saldo insuficiente ao tentar sacar um valor indisponível na conta.")
    void quandoInformadoUmValorSuperiorAoSaldo_DeveRetornarSaldoInsuficiente() {

        Account contaSalva = returnAccount(returnAccount());
        contaSalva.setAccountBalance(1000);

        Mockito.when(accountRepository.findByIdAndDeletedIsFalse(Mockito.any())).thenReturn(Optional.of(contaSalva));

        String mensagemRetorno = accountService.withdrawAccount(contaSalva.getId(), 1000.01);

        Assertions.assertEquals(mensagemRetorno, "Saldo insuficiente!");
    }

    @Test
    @DisplayName("Deve retornar valor inválido ao tentar sacar um valor igual ou menor que zero")
    void quandoInformadoUmValorInvalido_DeveRetornarSaqueNaoRealizado() {

        String mensagemRetorno = accountService.withdrawAccount(1L, 0);

        Assertions.assertEquals(mensagemRetorno, "Valor para saque inválido!");
    }

    @Test
    @DisplayName("Deve permitir depositar o valor na conta.")
    void quandoInformadoUmValor_DeveRetornarDepositoSucesso() {

        Account contaSalva = returnAccount(returnAccount());
        contaSalva.setAccountBalance(300);

        Mockito.when(accountRepository.findByIdAndDeletedIsFalse(Mockito.any())).thenReturn(Optional.of(contaSalva));

        String mensagemRetorno = accountService.depositAccount(contaSalva.getId(), 500);

        Assertions.assertEquals(mensagemRetorno, "deposito realizado!");
        Assertions.assertEquals(contaSalva.getAccountBalance(), 800);
    }

    @Test
    @DisplayName("Deve retornar a exception ao tentar depositar para uma conta inexistente.")
    void quandoInformadoUmValor_DeveRetornarExceptionDeposito() {

        Mockito.when(accountRepository.findByIdAndDeletedIsFalse(Mockito.any())).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> accountService.depositAccount(1L, 100));
    }

    @Test
    @DisplayName("Deve retornar a mensagem de valor inválido ao tentar depositar.")
    void quandoInformadoUmValor_DeveRetornarValorInvalidoDeposito() {

        Account contaSalva = returnAccount(returnAccount());

        String mensagemRetorno = accountService.depositAccount(contaSalva.getId(), 0);

        Assertions.assertEquals(mensagemRetorno, "Valor para deposito inválido!");
    }

    @Test
    @DisplayName("Deve retornar a mensagem de sucesso ao realizar transferência.")
    void quandoInformadoUmValor_DeveRetornarSucessoTranferencia() {
        Account contaOrigem = returnAccount(returnAccount());
        contaOrigem.setAccountBalance(1000);

        Account contaDestino = returnAccount(returnAccount());
        contaDestino.setId(2L);

        Mockito.when(accountRepository.findByIdAndDeletedIsFalse(contaOrigem.getId())).thenReturn(Optional.of(contaOrigem));
        Mockito.when(accountRepository.findByIdAndDeletedIsFalse(contaDestino.getId())).thenReturn(Optional.of(contaDestino));

        String mensagemRetorno = accountService.transferAccount(contaOrigem.getId(), contaDestino.getId(), 1000);
        Assertions.assertEquals(mensagemRetorno, "Transferência realizada com sucesso!");
        Assertions.assertEquals(contaOrigem.getAccountBalance(), 0);
        Assertions.assertEquals(contaDestino.getAccountBalance(), 1000);
    }

    @Test
    @DisplayName("Deve retornar exception para conta de origem inexistente.")
    void quandoInformadoUmValor_DeveRetornarExceptionContaOrigemInexistenteTranferencia() {

        Account contaOrigem = returnAccount(returnAccount());

        Mockito.when(accountRepository.findByIdAndDeletedIsFalse(contaOrigem.getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () ->
                accountService.transferAccount(contaOrigem.getId(), 2L, 100));
    }

    @Test
    @DisplayName("Deve retornar exception para conta de destino inexistente.")
    void quandoInformadoUmValor_DeveRetornarExceptionContaDestinoInexistenteTranferencia() {

        Account contaDestino = returnAccount(returnAccount());
        Account contaOrigem = returnAccount(returnAccount());

        Mockito.when(accountRepository.findByIdAndDeletedIsFalse(contaOrigem.getId())).thenReturn(Optional.of(contaOrigem));
        Mockito.when(accountRepository.findByIdAndDeletedIsFalse(contaDestino.getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () ->
                accountService.transferAccount(contaOrigem.getId(), contaDestino.getId(), 100));
    }

    @Test
    @DisplayName("Deve retornar a mensagem de valor inválido ao tentar transferir.")
    void quandoInformadoUmValor_DeveRetornarValorInvalidoTranferencia() {

        String mensagemRetorno = accountService.transferAccount(1L, 2L, 0);

        Assertions.assertEquals(mensagemRetorno, "Valor para transferência inválido!");
    }

    @Test
    @DisplayName("Deve retornar saldo insuficiente ao tentar transferir.")
    void quandoInformadoUmValor_DeveRetornarSaldoInsuficienteTranferencia() {

        Account contaOrigem = returnAccount(returnAccount());
        contaOrigem.setAccountBalance(1000);

        Account contaDestino = returnAccount(returnAccount());
        contaDestino.setId(2L);

        Mockito.when(accountRepository.findByIdAndDeletedIsFalse(contaOrigem.getId())).thenReturn(Optional.of(contaOrigem));
        Mockito.when(accountRepository.findByIdAndDeletedIsFalse(contaDestino.getId())).thenReturn(Optional.of(contaDestino));

        String mensagemRetorno = accountService.transferAccount(contaOrigem.getId(), contaDestino.getId(), 1001);

        Assertions.assertEquals(mensagemRetorno, "Saldo insuficiente para a transferência!");
    }

    private Account returnAccount(Client cliente) {
        Account conta = AccountBuilder.createAccount();
        conta.setId(1L);
        conta.setClient(cliente);
        return conta;
    }

    private Client returnAccount() {
        Client client = ClientBuilder.createClient();
        client.setId(1L);
        return client;
    }
}
