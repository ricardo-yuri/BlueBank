package com.br.panacademy.devcompilers.bluebank.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ContaDTO {

	/*
	public static class IdContaDTO implements Serializable {
		
		private static final long serialVersionUID = 1L;
		
		private Long cpfUsuario;
		private String agencia;
		private String numeroConta;
		
		public IdContaDTO(Long cpfUsuario, String agencia, String numeroConta) {
			this.cpfUsuario = cpfUsuario;
			this.agencia = agencia;
			this.numeroConta = numeroConta;
		}

		public Long getCpfUsuario() {
			return cpfUsuario;
		}

		public void setCpfUsuario(Long cpfUsuario) {
			this.cpfUsuario = cpfUsuario;
		}

		public String getAgencia() {
			return agencia;
		}

		public void setAgencia(String agencia) {
			this.agencia = agencia;
		}

		public String getNumeroConta() {
			return numeroConta;
		}

		public void setNumeroConta(String numeroConta) {
			this.numeroConta = numeroConta;
		}
			
	}
	
	@NotNull
	private IdContaDTO id;
	*/
	
	private Long id;
	
	private String cpfUsuario;
	
	@NotEmpty
	private String agencia;
	
	@NotEmpty
	private String numeroConta;
	
	@NotEmpty
	private String tipoConta;
	
	private double saldo;
	
	private String senha;
	
	private LocalDateTime createAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpfUsuario() {
		return cpfUsuario;
	}

	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
}
