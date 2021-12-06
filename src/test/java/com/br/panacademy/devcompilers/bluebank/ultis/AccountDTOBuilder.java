package com.br.panacademy.devcompilers.bluebank.ultis;

import com.br.panacademy.devcompilers.bluebank.dto.AccountDTO;
import com.br.panacademy.devcompilers.bluebank.enums.AccountType;

public class AccountDTOBuilder {

    public static AccountDTO createAccountDTO() {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setCpfUser("000.000.000-00");
        accountDTO.setAgency("071");
        accountDTO.setAccountNumber("1");
        accountDTO.setAccountType(AccountType.CORRENTE);
        accountDTO.setAccountBalance(0);

        return accountDTO;
    }
}
