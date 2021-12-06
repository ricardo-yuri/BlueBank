package com.br.panacademy.devcompilers.bluebank.dto;

import com.br.panacademy.devcompilers.bluebank.enums.AccountType;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class AccountDTO {

	private Long id;
	@NotEmpty
	@NotNull
	@Length(min = 11, max = 14)
	private String cpfUser;
	@NotEmpty
	@Length(max = 10)
	private String agency;
	@Length(max = 12)
	private String accountNumber;
	@NotEmpty
	private AccountType accountType;
	private double accountBalance;
	private LocalDateTime createAt;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getCpfUser() {
		return cpfUser;
	}
	public void setCpfUser(String cpfUser) {
		this.cpfUser = cpfUser;
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

	public LocalDateTime getCreateAt() {
		return createAt;
	}
	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
}
