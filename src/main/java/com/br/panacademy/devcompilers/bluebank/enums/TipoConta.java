package com.br.panacademy.devcompilers.bluebank.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoConta {


    CORRENTE(1, "Conta Corrente"),

    POUPANCA(2,"Conta Poupança"),

    SALARIO(3, "Conta Salário");

    private Integer id;
    private String descricao;

    TipoConta(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
