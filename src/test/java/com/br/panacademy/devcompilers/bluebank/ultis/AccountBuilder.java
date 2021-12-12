package com.br.panacademy.devcompilers.bluebank.ultis;

import com.br.panacademy.devcompilers.bluebank.entity.Client;
import com.br.panacademy.devcompilers.bluebank.entity.Account;
import com.br.panacademy.devcompilers.bluebank.entity.Historic;
import com.br.panacademy.devcompilers.bluebank.enums.AccountType;
import com.br.panacademy.devcompilers.bluebank.enums.OperationType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AccountBuilder {

    public static Account createAccount() {
        Client client = ClientBuilder.createClient();

        Account account = new Account();
        account.setClient(client);
        account.setAgency("071");
        account.setAccountNumber("0001-1");
        account.setAccountType(AccountType.CORRENTE);
        account.setAccountBalance(0);
        account.setDeleted(false);

        return account;
    }
}
