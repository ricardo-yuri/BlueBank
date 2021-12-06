package com.br.panacademy.devcompilers.bluebank.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.br.panacademy.devcompilers.bluebank.enums.ClientType;

public class ClientDTO {

	private Long id;
	
	@NotEmpty
	@Length(max = 150)
	private String name;
	
	@NotEmpty
	@Length(max = 14)
	private String cpfUser;
	
	@Length(max = 14)
	private String rg;

	private ContactDTO contact;

	@NotEmpty
	private AddressDTO address;
	
	@NotEmpty
	private ClientType clientType;
	
	private LocalDateTime createAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpfUser() {
		return cpfUser;
	}

	public void setCpfUser(String cpfUser) {
		this.cpfUser = cpfUser;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public ContactDTO getContact() {
		return contact;
	}

	public void setContact(ContactDTO contact) {
		this.contact = contact;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public ClientType getClientType() {
		return clientType;
	}

	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
}
