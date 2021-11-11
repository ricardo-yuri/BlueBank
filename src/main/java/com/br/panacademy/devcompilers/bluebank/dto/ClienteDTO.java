package com.br.panacademy.devcompilers.bluebank.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.br.panacademy.devcompilers.bluebank.enums.TipoCliente;

public class ClienteDTO {

	private Long id;
	
	@NotEmpty
	@Length(max = 150)
	private String nome;
	
	@NotEmpty
	@Length(max = 14)
	private String cpf;
	
	@Length(max = 14)
	private String rg;
	
	@NotEmpty
	private EnderecoDTO endereco;
	
	@NotEmpty
	private TipoCliente tipoCliente;
	
	private LocalDateTime createAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public EnderecoDTO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoDTO endereco) {
		this.endereco = endereco;
	}

	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
	
	
}
