package com.br.panacademy.devcompilers.bluebank.ultis;

import com.br.panacademy.devcompilers.bluebank.entity.Conta;
import com.br.panacademy.devcompilers.bluebank.enums.TipoConta;

public class ContaBuild {

    public static Conta criaContaSemId() {
        Conta conta = new Conta();
        //conta.setCpfUsuario("000.000.000-00");
        conta.setAgencia("071");
        conta.setNumeroConta("1");
        conta.setTipoConta(TipoConta.CORRENTE);
        conta.setSaldo(0);

        return conta;
    }
}
