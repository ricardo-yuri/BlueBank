package com.br.panacademy.devcompilers.bluebank.ultis;

import com.br.panacademy.devcompilers.bluebank.entity.Client;
import com.br.panacademy.devcompilers.bluebank.entity.Account;
import com.br.panacademy.devcompilers.bluebank.entity.Historic;
import com.br.panacademy.devcompilers.bluebank.enums.AccountType;
import com.br.panacademy.devcompilers.bluebank.enums.OperationType;

import java.util.Collections;

public class AccountBuilder {

    public static Account createAccount() {
        Client client = ClientBuilder.createClient();

        Account account = new Account();
        account.setClient(client);
        account.setAgency("071");
        account.setAccountNumber("0001-1");
        account.setAccountType(AccountType.CORRENTE);
        account.setHistoric(Collections.singletonList(new Historic()));
        account.setAccountBalance(0);
        account.setDeleted(false);

        return account;
    }

    private static Historic createHistoric() {
        Historic historic = new Historic();
        historic.setLog("");
        historic.setIdAccount(1L);
        historic.setOperationType(OperationType.SAQUE);
        return null;
    }
}
