package com.github.leosilvadev.issuer.v1.contract;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.github.leosilvadev.issuer.domain.Issuer;

public class RegistrationContract {

	@NotNull @NotEmpty
	private String name;

	@NotNull @NotEmpty
	private String documentNumber;

	@NotNull @NotEmpty
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Issuer asIssuer() {
		return new Issuer(name, documentNumber, email);
	}
	
}
