package com.github.leosilvadev.product.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.github.leosilvadev.product.domain.Product;
import com.github.leosilvadev.product.repository.ProductRepository;

@Component
public class ProductRegistrationConsumer {
	
	private ProductRepository repository;
	
	@Autowired
	public ProductRegistrationConsumer(ProductRepository repository) {
		this.repository = repository;
	}
	
	@JmsListener(destination = "products.register", containerFactory = "jmsListerContainerFactory")
    public void receiveMessage(Product product) {
        repository.save(product);
    }

}
