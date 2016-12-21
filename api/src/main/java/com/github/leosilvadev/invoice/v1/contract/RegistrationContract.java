package com.github.leosilvadev.invoice.v1.contract;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class RegistrationContract {

	@NotNull @NotEmpty
	private String documentNumber;

	@NotNull @NotEmpty
	private List<RegistrationProduct> products;
	
	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public List<RegistrationProduct> getProducts() {
		return products;
	}

	public void setProducts(List<RegistrationProduct> products) {
		this.products = products;
	}
	
}
