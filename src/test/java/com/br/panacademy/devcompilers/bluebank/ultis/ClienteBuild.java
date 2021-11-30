package com.br.panacademy.devcompilers.bluebank.ultis;

import com.br.panacademy.devcompilers.bluebank.entity.Cliente;
import com.br.panacademy.devcompilers.bluebank.entity.Contato;
import com.br.panacademy.devcompilers.bluebank.entity.Endereco;
import com.br.panacademy.devcompilers.bluebank.enums.TipoCliente;

import java.util.List;

public class ClienteBuild {

    public static Cliente criaCliente() {
        Cliente cliente = new Cliente();

        cliente.setNome("Cliente 1");
        cliente.setCpf("000.000.000-00");
        cliente.setRg("00.00.00-00");
        cliente.setEndereco(retornaEnderecoSemId());
        cliente.setTipoCliente(TipoCliente.PESSOA_FISICA);

        return cliente;
    }

    private static Endereco retornaEnderecoSemId() {
        Endereco endereco = new Endereco();

        endereco.setCep("00000-000");
        endereco.setLogradouro("Rua: x");
        endereco.setComplemento("Casa");
        endereco.setBairro("Bairro 1");
        endereco.setLocalidade("Localidade 1");
        endereco.setUf("SP");
        return endereco;
    }

    private static Contato retornaContatoSemId() {
        Contato contato = new Contato();

        contato.setCelular(List.of("123456789"));
        contato.setTelefone("12345678");
        contato.setEmail("teste@gmail.com");

        return contato;
    }
}
