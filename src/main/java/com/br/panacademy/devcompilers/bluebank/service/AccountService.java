package com.br.panacademy.devcompilers.bluebank.service;

import com.br.panacademy.devcompilers.bluebank.dto.AccountDTO;
import com.br.panacademy.devcompilers.bluebank.dto.HistoricDTO;
import com.br.panacademy.devcompilers.bluebank.entity.Account;
import com.br.panacademy.devcompilers.bluebank.entity.Client;
import com.br.panacademy.devcompilers.bluebank.entity.Historic;
import com.br.panacademy.devcompilers.bluebank.enums.OperationType;
import com.br.panacademy.devcompilers.bluebank.exceptions.AccountNotFoundException;
import com.br.panacademy.devcompilers.bluebank.exceptions.OperationIllegalException;
import com.br.panacademy.devcompilers.bluebank.repository.AccountRepository;
import com.br.panacademy.devcompilers.bluebank.repository.ClientRepository;
import com.br.panacademy.devcompilers.bluebank.repository.HistoricRepository;
import com.br.panacademy.devcompilers.bluebank.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private HistoricRepository historicRepository;

    public AccountDTO createAccount(AccountDTO accountDTO) {
        Account account;
        Optional<Client> client = clientRepository.findByCpf(accountDTO.getCpfUser());
        if (client.isPresent()) {
            account = Mapper.toAccount(accountDTO);
            account.setClient(client.get());
            account.setAccountNumber(generatorNumberAccount(client.get().getId()));
        } else {
            throw new NoSuchElementException("Cliente não encontrado");
        }
        Account accountToSave = accountRepository.save(account);
        return Mapper.toAccountDTO(accountToSave);
    }

    public AccountDTO findByIdAccount(Long id) {
        Account account = verifyIfExists(id);
        return Mapper.toAccountDTO(account);
    }

    public AccountDTO updateAccount(AccountDTO accountDTO) {
        Account accountUpdate = verifyIfExists(accountDTO.getId());
        //Não deve permitir alterar o titular da conta
        if (!accountUpdate.getClient().getCpf().equals(accountDTO.getCpfUser())) {
            throw new OperationIllegalException("Não é permitido mudar o titular da conta!");
        }
        //Não deve permitir alterar o número da conta
        if (!accountUpdate.getAccountNumber().equals(accountDTO.getAccountNumber())) {
            throw new OperationIllegalException("Não é permitido alterar o número da conta!");
        }
        Account accountSaved = accountRepository.save(accountUpdate);
        return Mapper.toAccountDTO(accountSaved);
    }

    public String deleteAccount(Long id) {
        Account accountToDelete = verifyIfExists(id);
        if (accountToDelete.getAccountBalance() > 0) {
            throw new OperationIllegalException("Não é permitido deletar uma conta com saldo!");
        }
        accountToDelete.setDeleted(true);
        accountRepository.save(accountToDelete);
        return String.format("Conta com Id: %d deletada!", id);
    }

    //Método sacar
    public String withdrawAccount(Long idConta, double valor) {

        if (isValorInvalido(valor)) return "Valor para saque inválido!";

        Account account = verifyIfExists(idConta);

        double saldoAtual = account.getAccountBalance();
        if (isSaldoSuficiente(saldoAtual, valor)) {
            saldoAtual -= valor;
            account.setAccountBalance(saldoAtual);
            accountRepository.save(account);

            String logToSave =
                    String.format(MessageFormat.format("{0} Saque -> conta %s, valor: %f",
                            dateHistoric()), account.getAccountNumber(), formatValueHistoric(valor));

            createHistoric(logToSave, account, OperationType.SAQUE);

            return "Saque realizado!";

        } else {
            return "Saldo insuficiente!";
        }
    }

    //Método depositar
    public String depositAccount(Long idConta, double valor) {

        if (isValorInvalido(valor)) return "Valor para deposito inválido!";

        Account account = verifyIfExists(idConta);
        double saldoAtual = account.getAccountBalance();
        account.setAccountBalance(saldoAtual + valor);
        accountRepository.save(account);

        String logToSave =
                String.format(MessageFormat.format("{0} Deposito -> conta: %s, valor: %s",
                        dateHistoric()), account.getAccountNumber(), formatValueHistoric(valor));
        createHistoric(logToSave, account, OperationType.DEPOSITO);

        return "deposito realizado!";
    }

    //Método transferir
    //TODO Revisar a anotação @Transactional
    @Transactional
    public String transferAccount(Long idContaOrigem, Long idContaDestino, double valor) {

        if (isValorInvalido(valor)) return "Valor para transferência inválido!";

        Account accountOrigem = verifyIfExists(idContaOrigem);
        Account accountDestino = verifyIfExists(idContaDestino);

        double saldoAtualContaOrigem = accountOrigem.getAccountBalance();
        if (isSaldoSuficiente(saldoAtualContaOrigem, valor)) {
            accountOrigem.setAccountBalance(saldoAtualContaOrigem - valor);
            accountRepository.save(accountOrigem);
            double saldoAtualContaDestino = accountDestino.getAccountBalance();
            accountDestino.setAccountBalance(saldoAtualContaDestino + valor);
            accountRepository.save(accountDestino);
            String logAccountOrigin =
                    String.format(MessageFormat.format("{0} Transferência: -> conta: %s para conta %s, valor: %f",
                            dateHistoric()),
                            accountOrigem.getAccountNumber(),
                            accountDestino.getAccountNumber(),
                            formatValueHistoric(valor));

            String logAccountDestination =
                    String.format(MessageFormat.format("{0} Transferência: Transferência recebida da conta: %s no valor de %f",
                            dateHistoric()),
                            accountOrigem.getAccountNumber(),
                            formatValueHistoric(valor));

            createHistoric(logAccountOrigin, accountOrigem, OperationType.TRANSFERENCIA);
            createHistoric(logAccountDestination, accountDestino, OperationType.TRANSFERENCIA);

            return "Transferência realizada com sucesso!";
        } else {
            return "Saldo insuficiente para a transferência!";
        }
    }
    //TODO mudar essa consulta para buscar por data
    public HistoricDTO findByIdAccountHistoric(Long id) {
        return
                historicRepository.findByIdAndIdAccount(id, 1L)
                        .map(Mapper::toHistoricDTO)
                        .orElseThrow(() -> new NoSuchElementException(
                                String.format("Histórico com ID: %d não encontrado!", id)));
    }

    public List<HistoricDTO> listHistoricIdAccount(Long id) {
        Optional<List<Historic>> listHistoric = historicRepository.findAllByIdAccount(id);

        if(listHistoric.isEmpty()) throw new AccountNotFoundException(id);

        return listHistoric.get().stream()
                .map(Mapper::toHistoricDTO)
                .collect(Collectors.toList());
    }

    private void createHistoric(String log, Account account, OperationType type) {
        Historic historic = new Historic();
        historic.setLog(log);
        historic.setIdAccount(account.getId());
        historic.setOperationType(type);

        account.getHistoric().add(historic);

        historicRepository.save(historic);
        accountRepository.save(account);
    }

    private Account verifyIfExists(Long id) {
        return accountRepository.findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
    }

    private boolean isSaldoSuficiente(double saldo, double valor) {
        if ((saldo - valor) >= 0) return true;
        return false;
    }

    private boolean isValorInvalido(double valor) {
        if (valor < 0.01) return true;
        return false;
    }

    private String generatorNumberAccount(Long idClient) {
        Random generator = new Random();
        //TODO ver uma forma alternativa de gerar número conta.
        return String.valueOf(generator.nextInt(999999)) + idClient;
    }

    private String formatValueHistoric(Double value) {
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        return numberFormat.format(value);
    }

    private String dateHistoric() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }
}
