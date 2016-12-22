package com.github.leosilvadev.product.v1.contract;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class RegistrationContract {

	@NotNull @NotEmpty
	private String name;
	
	@NotNull
	private BigDecimal price;
	
	public RegistrationContract() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
