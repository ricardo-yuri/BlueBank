package com.br.panacademy.devcompilers.bluebank.service;

import com.br.panacademy.devcompilers.bluebank.dto.AccountDTO;
import com.br.panacademy.devcompilers.bluebank.entity.Account;
import com.br.panacademy.devcompilers.bluebank.entity.Client;
import com.br.panacademy.devcompilers.bluebank.exceptions.OperationIllegalException;
import com.br.panacademy.devcompilers.bluebank.repository.AccountRepository;
import com.br.panacademy.devcompilers.bluebank.repository.ClientRepository;
import com.br.panacademy.devcompilers.bluebank.repository.HistoricRepository;
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

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
@DisplayName("Testes para a classe Conta Service")
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private HistoricRepository historicRepository;

    @InjectMocks
    private AccountService accountService;


    @Test
    @DisplayName("Retorna a conta criada com sucesso.")
    void whenInformedAccount_returnCreateAccount() {

        Account accountExpected = returnAccount(returnAccount());

        Mockito.when(clientRepository.findByCpf(Mockito.any())).thenReturn(Optional.of(returnAccount()));
        Mockito.when(accountRepository.save(Mockito.any())).thenReturn(accountExpected);

        AccountDTO accountDTO = AccountDTOBuilder.createAccountDTO();
        AccountDTO accountSaved = accountService.createAccount(accountDTO);

        Assertions.assertEquals(accountExpected.getId(), accountSaved.getId());
        Assertions.assertEquals(accountExpected.getAccountNumber(), accountSaved.getAccountNumber());
        Assertions.assertEquals(accountExpected.getClient().getCpf(), accountSaved.getCpfUser());
    }

    @Test
    @DisplayName("Não deve criar a conta para um cliente inexistente.")
    void whenInformedAccount_returnNoSuchElementClientException() {

        Mockito.when(clientRepository.findByCpf(Mockito.any())).thenReturn(Optional.empty());

        AccountDTO accountDTO = AccountDTOBuilder.createAccountDTO();

        Assertions.assertThrows(NoSuchElementException.class, () -> accountService.createAccount(accountDTO));
    }

    @Test
    @DisplayName("Deve retornar a conta por ID.")
    void whenInformedAccount_returnAccount() {
        Account accountExpected = returnAccount(returnAccount());

        Mockito.when(accountRepository.findByIdAndDeletedIsFalse(Mockito.any())).thenReturn(Optional.of(accountExpected));

        AccountDTO returnedAccount = accountService.findByIdAccount(accountExpected.getId());

        Assertions.assertEquals(accountExpected.getId(), returnedAccount.getId());
        Assertions.assertEquals(accountExpected.getClient().getCpf(), returnedAccount.getCpfUser());
    }

    @Test
    @DisplayName("Deve retornar Exception ao bucar uma conta por ID.")
    void whenInformedAccount_returnNoSuchElementException() {

        Mockito.when(accountRepository.findByIdAndDeletedIsFalse(Mockito.any())).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> accountService.findByIdAccount(1L));
    }

    @Test
    @DisplayName("Deve retornar a conta deletada com sucesso.")
    void whenInformedAccount_returnAccountIsDeleted() {

        Account accountSaved = returnAccount(returnAccount());
        accountSaved.setDeleted(true);

        Mockito.when(accountRepository.findByIdAndDeletedIsFalse(Mockito.any())).thenReturn(Optional.of(accountSaved));
        Mockito.when(accountRepository.save(Mockito.any())).thenReturn(accountSaved);

        String messageReturned = accountService.deleteAccount(1L);

        Assertions.assertEquals(String.format("Conta com Id: %d deletada!", accountSaved.getId()), messageReturned);
    }

    @Test
    @DisplayName("Deve retornar uma exception ao tentar deletar uma conta que não existe.")
    void whenInformedAccount_returnNoSuchElementExceptionForAccountExists() {

        Mockito.when(accountRepository.findByIdAndDeletedIsFalse(Mockito.any())).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> accountService.deleteAccount(1L));
    }

    @Test
    @DisplayName("Deve retornar uma exception ao tentar mudar o titular da conta.")
    void whenInformedAccount_returnNoSuchElementExceptionToChangingAccountHolder() {

        Account accountExpected = returnAccount(returnAccount());

        AccountDTO accountUpdate = AccountDTOBuilder.createAccountDTO();
        accountUpdate.setId(1L);
        accountUpdate.setCpfUser("000.000.000.00");

        Mockito.when(accountRepository.findByIdAndDeletedIsFalse(Mockito.any())).thenReturn(Optional.of(accountExpected));

        Assertions.assertThrows(OperationIllegalException.class, () -> accountService.updateAccount(accountUpdate));
    }

    @Test
    @DisplayName("Deve retornar uma exception ao tentar mudar o número da conta.")
    void whenInformedAccount_returnNoSuchElementExceptionToChangingNumberAccount() {

        Account accountExpected = returnAccount(returnAccount());

        AccountDTO accountUpdate = AccountDTOBuilder.createAccountDTO();
        accountUpdate.setId(1L);
        accountUpdate.setAccountNumber("0002-4");

        Mockito.when(accountRepository.findByIdAndDeletedIsFalse(Mockito.any())).thenReturn(Optional.of(accountExpected));

        Assertions.assertThrows(OperationIllegalException.class, () -> accountService.updateAccount(accountUpdate));
    }

    @Test
    @DisplayName("Deve permitir sacar valor da conta.")
    void whenInformedValue_returnWithdrawSuccess() {

        Account accountSaved = returnAccount(returnAccount());
        accountSaved.setAccountBalance(1000);

        accountSaved.setHistoric(Collections.emptyList());

        Mockito.when(accountRepository.findByIdAndDeletedIsFalse(Mockito.any())).thenReturn(Optional.of(accountSaved));
        Mockito.when(accountRepository.save(accountSaved)).thenReturn(accountSaved);

        String messageReturn = accountService.withdrawAccount(accountSaved.getId(), 900);

        Assertions.assertEquals("Saque realizado!", messageReturn);
        Assertions.assertEquals(accountSaved.getAccountBalance(), 100);
    }

    @Test
    @DisplayName("Deve retornar uma exception ao tentar sacar um valor de uma conta inexistente.")
    void whenInformedValue_returnAccountNotFound() {

        Mockito.when(accountRepository.findByIdAndDeletedIsFalse(Mockito.any())).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> accountService.withdrawAccount(1L, 100));
    }

    @Test
    @DisplayName("Deve retornar saldo insuficiente ao tentar sacar um valor indisponível na conta.")
    void whenInformedValueTop_returnInsufficientBalance() {

        Account accountSaved = returnAccount(returnAccount());
        accountSaved.setAccountBalance(1000);

        Mockito.when(accountRepository.findByIdAndDeletedIsFalse(Mockito.any())).thenReturn(Optional.of(accountSaved));

        String messageReturned = accountService.withdrawAccount(accountSaved.getId(), 1000.01);

        Assertions.assertEquals(messageReturned, "Saldo insuficiente!");
    }

    @Test
    @DisplayName("Deve retornar valor inválido ao tentar sacar um valor igual ou menor que zero")
    void whenInformedValueInvalid_returnWithdrawInvalid() {

        String messageReturn = accountService.withdrawAccount(1L, 0);

        Assertions.assertEquals(messageReturn, "Valor para saque inválido!");
    }

    @Test
    @DisplayName("Deve permitir depósitar o valor na conta.")
    void whenInformedValue_returnDepositSuccess() {

        Account accountSaved = returnAccount(returnAccount());
        accountSaved.setAccountBalance(300);
        accountSaved.setHistoric(Collections.emptyList());

        Mockito.when(accountRepository.findByIdAndDeletedIsFalse(Mockito.any())).thenReturn(Optional.of(accountSaved));

        String messageReturned = accountService.depositAccount(accountSaved.getId(), 500);

        Assertions.assertEquals(messageReturned, "depósito realizado!");
        Assertions.assertEquals(accountSaved.getAccountBalance(), 800);
    }

    @Test
    @DisplayName("Deve retornar a exception ao tentar depósitar para uma conta inexistente.")
    void whenInformedValue_returnExceptionDeposit() {

        Mockito.when(accountRepository.findByIdAndDeletedIsFalse(Mockito.any())).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> accountService.depositAccount(1L, 100));
    }

    @Test
    @DisplayName("Deve retornar a mensagem de valor inválido ao tentar depósitar.")
    void whenInformedValue_returnInvalidValueDeposit() {

        Account accountSaved = returnAccount(returnAccount());

        String messageReturned = accountService.depositAccount(accountSaved.getId(), 0);

        Assertions.assertEquals(messageReturned, "Valor para depósito inválido!");
    }

    @Test
    @DisplayName("Deve retornar a mensagem de sucesso ao realizar transferência.")
    void whenInformedValue_returnSuccessTransfer() {
        Account accountOrigen = returnAccount(returnAccount());
        accountOrigen.setAccountBalance(1000);
        accountOrigen.setHistoric(Collections.emptyList());

        Account accountDestination = returnAccount(returnAccount());
        accountDestination.setId(2L);
        accountDestination.setHistoric(Collections.emptyList());

        Mockito.when(accountRepository.findByIdAndDeletedIsFalse(accountOrigen.getId())).thenReturn(Optional.of(accountOrigen));
        Mockito.when(accountRepository.findByIdAndDeletedIsFalse(accountDestination.getId())).thenReturn(Optional.of(accountDestination));

        String messageReturned = accountService.transferAccount(accountOrigen.getId(), accountDestination.getId(), 1000);
        Assertions.assertEquals(messageReturned, "Transferência realizada com sucesso!");
        Assertions.assertEquals(accountOrigen.getAccountBalance(), 0);
        Assertions.assertEquals(accountDestination.getAccountBalance(), 1000);
    }

    @Test
    @DisplayName("Deve retornar exception para conta de origem inexistente.")
    void whenInformedValue_returnExceptionAccountOriginNotFound() {

        Account accountOrigen = returnAccount(returnAccount());

        Mockito.when(accountRepository.findByIdAndDeletedIsFalse(accountOrigen.getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () ->
                accountService.transferAccount(accountOrigen.getId(), 2L, 100));
    }

    @Test
    @DisplayName("Deve retornar exception para conta de destino inexistente.")
    void whenInformedValue_returnExceptionAccountDestinationNotFound() {

        Account accountDestination = returnAccount(returnAccount());
        Account accountOrigen = returnAccount(returnAccount());

        Mockito.when(accountRepository.findByIdAndDeletedIsFalse(accountOrigen.getId())).thenReturn(Optional.of(accountOrigen));
        Mockito.when(accountRepository.findByIdAndDeletedIsFalse(accountDestination.getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () ->
                accountService.transferAccount(accountOrigen.getId(), accountDestination.getId(), 100));
    }

    @Test
    @DisplayName("Deve retornar a mensagem de valor inválido ao tentar transferir.")
    void whenInformedValue_returnValueInvalidTransfer() {

        String mensagemRetorno = accountService.transferAccount(1L, 2L, 0);

        Assertions.assertEquals(mensagemRetorno, "Valor para transferência inválido!");
    }

    @Test
    @DisplayName("Deve retornar saldo insuficiente ao tentar transferir.")
    void whenInformedValue_returnInsufficientBalanceTransfer() {

        Account accountOrigin = returnAccount(returnAccount());
        accountOrigin.setAccountBalance(1000);

        Account accountDestination = returnAccount(returnAccount());
        accountDestination.setId(2L);

        Mockito.when(accountRepository.findByIdAndDeletedIsFalse(accountOrigin.getId())).thenReturn(Optional.of(accountOrigin));
        Mockito.when(accountRepository.findByIdAndDeletedIsFalse(accountDestination.getId())).thenReturn(Optional.of(accountDestination));

        String messageReturned = accountService.transferAccount(accountOrigin.getId(), accountDestination.getId(), 1001);

        Assertions.assertEquals(messageReturned, "Saldo insuficiente para a transferência!");
    }

    private Account returnAccount(Client client) {
        Account conta = AccountBuilder.createAccount();
        conta.setId(1L);
        conta.setClient(client);
        return conta;
    }

    private Client returnAccount() {
        Client client = ClientBuilder.createClient();
        client.setId(1L);
        return client;
    }
}
