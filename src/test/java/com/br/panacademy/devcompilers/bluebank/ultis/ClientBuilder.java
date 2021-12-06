package com.br.panacademy.devcompilers.bluebank.ultis;

import com.br.panacademy.devcompilers.bluebank.entity.Client;
import com.br.panacademy.devcompilers.bluebank.entity.Contact;
import com.br.panacademy.devcompilers.bluebank.entity.Address;
import com.br.panacademy.devcompilers.bluebank.enums.ClientType;

import java.util.List;

public class ClientBuilder {

    public static Client createClient() {
        Client client = new Client();

        client.setName("Cliente 1");
        client.setCpf("000.000.000-00");
        client.setRg("00.00.00-00");
        client.setEndereco(createAddress());
        client.setTipoCliente(ClientType.PESSOA_FISICA);

        return client;
    }

    private static Address createAddress() {
        Address address = new Address();

        address.setId(1L);
        address.setCep("00000-000");
        address.setPublicPlace("Rua: x");
        address.setComplement("Casa");
        address.setDistrict("Bairro 1");
        address.setLocality("Localidade 1");
        address.setUf("SP");
        return address;
    }

    private static Contact createContact() {
        Contact contact = new Contact();

        contact.setCell(List.of("123456789"));
        contact.setTelephone("12345678");
        contact.setEmail("teste@gmail.com");

        return contact;
    }
}
