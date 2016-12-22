package com.github.leosilvadev.product.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.github.leosilvadev.product.repository.ProductRepository;
import com.github.leosilvadev.product.service.ProductBuilder;
import com.github.leosilvadev.product.v1.contract.RegistrationContract;

@Component
public class ProductRegistrationConsumer {
	
	private ProductRepository repository;
	private ProductBuilder productBuilder;
	
	@Autowired
	public ProductRegistrationConsumer(ProductRepository repository, ProductBuilder productBuilder) {
		this.repository = repository;
		this.productBuilder = productBuilder;
	}
	
	@JmsListener(destination = "products.register", containerFactory = "jmsListerContainerFactory")
    public void receiveMessage(RegistrationContract contract) {
        repository.save(productBuilder.build(contract));
    }

}
