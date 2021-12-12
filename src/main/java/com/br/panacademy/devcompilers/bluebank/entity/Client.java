package com.br.panacademy.devcompilers.bluebank.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import com.br.panacademy.devcompilers.bluebank.enums.ClientType;

@Entity(name = "tb_client")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;
	@Column(nullable = false, unique = true)
	private String cpf;
	@Column
	private String rg;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "id_address")
	private Address address;

	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_contact")
	private Contact contact;

	@Column(nullable = false)
	private ClientType clientType;

	@ManyToOne(targetEntity = Account.class)
	@JoinColumn(name = "id_account")
	private List<Account> account;

	@Column
	@CreationTimestamp
	private LocalDateTime createAt;
	
	@Column(columnDefinition = "boolean default false")
	private boolean deleted;

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

	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}

	public Address getEndereco() {
		return address;
	}
	public void setEndereco(Address address) {
		this.address = address;
	}

	public Contact getContato() {
		return contact;
	}

	public void setContato(Contact contact) {
		this.contact = contact;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public List<Account> getConta() {
		return account;
	}

	public void setConta(List<Account> account) {
		this.account = account;
	}

	public ClientType getTipoCliente() {
		return clientType;
	}
	public void setTipoCliente(ClientType clientType) {
		this.clientType = clientType;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}
	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + name + ", cpf=" + cpf + ", rg=" + rg + ", endereco=" + address
				+ ", tipoCliente=" + clientType + ", createAt=" + createAt + "]";
	}
}
