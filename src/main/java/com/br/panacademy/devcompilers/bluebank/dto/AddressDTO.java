package com.br.panacademy.devcompilers.bluebank.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class AddressDTO {

	private Long id;
	
	@NotEmpty
	@Length(max = 9)
	private String cep;

	@Length(max = 50)
	private String publicPlace;

	@Length(max = 50)
	private String complement;
	
	@NotEmpty
	@Length(max = 50)
	private String district;
	
	@NotEmpty
	@Length(max = 50)
	private String locality;
	
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

	public String getPublicPlace() {
		return publicPlace;
	}

	public void setPublicPlace(String publicPlace) {
		this.publicPlace = publicPlace;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
}
