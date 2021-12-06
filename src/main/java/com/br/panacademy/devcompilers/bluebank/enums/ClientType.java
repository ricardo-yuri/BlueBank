package com.br.panacademy.devcompilers.bluebank.enums;

public enum ClientType {

	PESSOA_FISICA(1, "Pessoa Fisica"),
	PESSOA_JURIDICA(2, "Pessoa Juridica");
	
	private Integer id;
	private String description;

	ClientType(Integer id, String description) {
		this.id = id;
		this.description = description;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
