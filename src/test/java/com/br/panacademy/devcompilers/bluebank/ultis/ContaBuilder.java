package com.br.panacademy.devcompilers.bluebank.ultis;

import com.br.panacademy.devcompilers.bluebank.entity.Cliente;
import com.br.panacademy.devcompilers.bluebank.entity.Conta;
import com.br.panacademy.devcompilers.bluebank.enums.TipoConta;

public class ContaBuilder {

    public static Conta criaConta() {
        Cliente cliente = ClienteBuilder.criaCliente();

        Conta conta = new Conta();
        conta.setCliente(cliente);
        conta.setAgencia("071");
        conta.setNumeroConta("0001-1");
        conta.setTipoConta(TipoConta.CORRENTE);
        conta.setSaldo(0);
        conta.setDeletada(false);

        return conta;
    }
}
