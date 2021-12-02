package com.br.panacademy.devcompilers.bluebank.ultis;

import com.br.panacademy.devcompilers.bluebank.dto.ContaDTO;
import com.br.panacademy.devcompilers.bluebank.enums.TipoConta;

public class ContaDTOBuilder {

    public static ContaDTO criaContaDTO() {
        ContaDTO contaDTO = new ContaDTO();
        contaDTO.setCpfUsuario("000.000.000-00");
        contaDTO.setAgencia("071");
        contaDTO.setNumeroConta("0001-1");
        contaDTO.setTipoConta(TipoConta.CORRENTE);
        contaDTO.setSaldo(0);

        return contaDTO;
    }
}