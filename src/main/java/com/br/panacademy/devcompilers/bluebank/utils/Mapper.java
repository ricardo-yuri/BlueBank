package com.br.panacademy.devcompilers.bluebank.utils;

import com.br.panacademy.devcompilers.bluebank.dto.*;
import com.br.panacademy.devcompilers.bluebank.entity.*;
import org.springframework.http.ResponseEntity;

public class Mapper {

	public static Client toClient(ClientDTO clientDTO) {
		
		Client client = new Client();

		client.setId(clientDTO.getId());
		client.setName(clientDTO.getName());
		client.setCpf(clientDTO.getCpfUser());
		client.setRg(clientDTO.getRg());
		client.setContato(toContact(clientDTO.getContact()));
		client.setEndereco(toAddress(clientDTO.getAddress()));
		client.setTipoCliente(clientDTO.getClientType());
		client.setCreateAt(clientDTO.getCreateAt());
		
		return client;
	}
	
	public static ClientDTO toClientDTO(Client client) {
		
		ClientDTO clientDTO = new ClientDTO();
		
		clientDTO.setId(client.getId());
		clientDTO.setName(client.getName());
		clientDTO.setCpfUser(client.getCpf());
		clientDTO.setRg(client.getRg());
		clientDTO.setContact(toContactDTO(client.getContato()));
		clientDTO.setAddress(toAddressDTO(client.getEndereco()));
		clientDTO.setClientType(client.getTipoCliente());
		clientDTO.setCreateAt(client.getCreateAt());
		
		return clientDTO;
	}

	public static ContactDTO toContactDTO(Contact contactEntity) {
		ContactDTO contactDTO = new ContactDTO();

		contactDTO.setId(contactEntity.getId());
		contactDTO.setEmail(contactEntity.getEmail());
		contactDTO.setTelefone(contactEntity.getTelephone());
		contactDTO.setCelular(contactEntity.getCell());
		contactDTO.setCreateAt(contactEntity.getCreateAt());

		return contactDTO;
	}

	public static Contact toContact(ContactDTO contactDTO) {
		Contact contact = new Contact();

		contact.setId(contactDTO.getId());
		contact.setEmail(contactDTO.getEmail());
		contact.setTelephone(contactDTO.getTelefone());
		contact.setCell(contactDTO.getCelular());
		contact.setCreateAt(contactDTO.getCreateAt());

		return contact;
	}

	public static AddressDTO toAddressDTO(Address address) {
		AddressDTO addressDTO = new AddressDTO();
		
		addressDTO.setId(address.getId());
		addressDTO.setCep(address.getCep());
		addressDTO.setLogradouro(address.getPublicPlace());
		addressDTO.setComplemento(address.getComplement());
		addressDTO.setLocalidade(address.getLocality());
		addressDTO.setUf(address.getUf());
		addressDTO.setBairro(address.getDistrict());
		
		return addressDTO;
	}
	
	public static Address toAddress(AddressDTO addressDTO) {
		Address addressEntity = new Address();
		
		addressEntity.setId(addressDTO.getId());
		addressEntity.setCep(addressDTO.getCep());
		addressEntity.setPublicPlace(addressDTO.getLogradouro());
		addressEntity.setComplement(addressDTO.getComplemento());
		addressEntity.setLocality(addressDTO.getLocalidade());
		addressEntity.setUf(addressDTO.getUf());
		addressEntity.setDistrict(addressDTO.getBairro());
		
		return addressEntity;
	}
	
	public static Address responseEntityAddressToEntity(ResponseEntity<AddressDTO> addressDTO) {
		Address addressEntity = new Address();
		
		addressEntity.setId(addressDTO.getBody().getId());
		addressEntity.setCep(addressDTO.getBody().getCep());
		addressEntity.setPublicPlace(addressDTO.getBody().getLogradouro());
		addressEntity.setComplement(addressDTO.getBody().getComplemento());
		addressEntity.setLocality(addressDTO.getBody().getLocalidade());
		addressEntity.setUf(addressDTO.getBody().getUf());
		addressEntity.setDistrict(addressDTO.getBody().getBairro());
		
		return addressEntity;
	}
	
	public static AddressDTO responseEntityAddressToDTO(ResponseEntity<AddressDTO> address) {
		AddressDTO addressDTO = new AddressDTO();
		
		addressDTO.setId(address.getBody().getId());
		addressDTO.setCep(address.getBody().getCep());
		addressDTO.setLogradouro(address.getBody().getLogradouro());
		addressDTO.setComplemento(address.getBody().getComplemento());
		addressDTO.setLocalidade(address.getBody().getLocalidade());
		addressDTO.setUf(address.getBody().getUf());
		addressDTO.setBairro(address.getBody().getBairro());
		
		return addressDTO;
	}
	
	public static Account toAccount(AccountDTO accountDTO) {
		Account accountEntity = new Account();

		accountEntity.setId(accountDTO.getId());
		accountEntity.setAgency(accountDTO.getAgency());
		accountEntity.setAccountNumber(accountDTO.getAccountNumber());
		accountEntity.setAccountType(accountDTO.getAccountType());
		accountEntity.setAccountBalance(accountDTO.getAccountBalance());
		accountEntity.setCreateAt(accountDTO.getCreateAt());
		
		return accountEntity;
	}
	
	public static AccountDTO toAccountDTO(Account accountEntity) {
		AccountDTO accountDTO = new AccountDTO();

		accountDTO.setId(accountEntity.getId());
		accountDTO.setCpfUser(accountEntity.getClient().getCpf());
		accountDTO.setAgency(accountEntity.getAgency());
		accountDTO.setAccountNumber(accountEntity.getAccountNumber());
		accountDTO.setAccountType(accountEntity.getAccountType());
		accountDTO.setAccountBalance(accountEntity.getAccountBalance());
		accountDTO.setCreateAt(accountEntity.getCreateAt());
		
		return accountDTO;
	}

	public static HistoricDTO toHistoricDTO(Historic historicEntity) {
		HistoricDTO historicDTO = new HistoricDTO();

		historicDTO.setId(historicEntity.getId());
		historicDTO.setLog(historicEntity.getLog());
		historicDTO.setOperationType(historicEntity.getOperationType());
		historicDTO.setCreateAt(historicEntity.getCreateAt());

		return historicDTO;
	}

	public static Historic toHistoric(HistoricDTO historicDTO) {
		Historic historicEntity = new Historic();

		historicEntity.setId(historicDTO.getId());
		historicEntity.setLog(historicDTO.getLog());
		historicEntity.setOperationType(historicDTO.getOperationType());
		historicEntity.setCreateAt(historicDTO.getCreateAt());

		return historicEntity;
	}
}
