package com.github.leosilvadev.invoice.v1.contract;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class RegistrationProduct {
	
	@NotNull @NotEmpty
	private String product;
	
	@NotNull @Min(1)
	private Integer quantity;
	
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
