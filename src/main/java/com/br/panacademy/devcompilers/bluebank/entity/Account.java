package com.br.panacademy.devcompilers.bluebank.entity;

import com.br.panacademy.devcompilers.bluebank.enums.AccountType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "tb_account")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_account")
	private Long id;

	@ManyToOne(targetEntity = Client.class)
	@JoinColumn(name = "id_client")
	private Client client;
	
	@Column(nullable = false, length = 10)
	private String agency;

	@Column(nullable = false, unique = true, length = 12)
	private String accountNumber;

	@Column(nullable = false)
	private AccountType accountType;
	
	@Column
	private double accountBalance;

	@Column(columnDefinition = "boolean default false")
	private boolean deleted;
/*
	@ManyToMany
	@JoinTable(name="account_historic", joinColumns=
			{@JoinColumn(name="id_client")}, inverseJoinColumns=
			{@JoinColumn(name="id_historic")})
	private List<Historic> historic;
*/
	@OneToMany
	private List<Historic> historic;

	@Column
	@CreationTimestamp
	private LocalDateTime createAt;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}

	public String getAgency() {
		return agency;
	}
	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deletada) {
		this.deleted = deletada;
	}

	public List<Historic> getHistoric() {
		return historic;
	}
	public void setHistoric(List<Historic> historic) {
		this.historic = historic;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}
	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
}
