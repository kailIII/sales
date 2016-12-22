package com.github.leosilvadev.product.service;

import org.springframework.stereotype.Service;

import com.github.leosilvadev.product.domain.Product;
import com.github.leosilvadev.product.v1.contract.RegistrationContract;

@Service
public class ProductBuilder {
	
	public Product build(RegistrationContract contract) {
		return new Product(contract.getName(), contract.getPrice());
	}

}
