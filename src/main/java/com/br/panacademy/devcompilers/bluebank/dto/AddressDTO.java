package com.br.panacademy.devcompilers.bluebank.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class AddressDTO {

	private Long id;
	
	@NotEmpty
	@Length(max = 9)
	private String cep;

	@Length(max = 50)
	private String logradouro;

	@Length(max = 50)
	private String complemento;
	
	@NotEmpty
	@Length(max = 50)
	private String bairro;
	
	@NotEmpty
	@Length(max = 50)
	private String localidade;
	
	@NotEmpty
	@Length(max = 2)
	private String uf;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
    
	
}
