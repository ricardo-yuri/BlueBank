package com.br.panacademy.devcompilers.bluebank.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.*;


import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name = "tb_conta")
public class Conta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/*
	@Embeddable
	public static class IdConta implements Serializable {
		
		private static final long serialVersionUID = 1L;
		
		private Long cpfUsuario;
		private String agencia;
		private String numeroConta;
		
		public IdConta() {}

		public IdConta(Long cpfUsuario, String agencia, String numeroConta) {
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
	*/
	//@EmbeddedId
	//@Column(name = "id_conta")
	//private IdConta id;
	
	@Column(nullable = false, unique = true)
	private String cpfUsuario;
	
	@Column(nullable = false)
	private String agencia;
	
	@Column(nullable = false, unique = true)
	private String numeroConta;
	
	
	@Column(nullable = false)
	private String tipoConta;
	
	@Column
	private double saldo;
	
	@Column
	private String senha;
	
	@Column
	@CreationTimestamp
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
