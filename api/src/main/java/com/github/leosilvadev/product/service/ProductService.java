package com.github.leosilvadev.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.leosilvadev.product.domain.Product;
import com.github.leosilvadev.product.repository.ProductRepository;
import com.github.leosilvadev.product.v1.contract.RegistrationContract;

@Service
public class ProductService {

	private JmsTemplate jmsTemplate;
	private ProductRepository repository;
	
	@Autowired
	public ProductService(JmsTemplate jmsTemplate, ProductRepository repository) {
		this.jmsTemplate = jmsTemplate;
		this.repository = repository;
	}
	
	public List<Product> findAllActives() {
		return repository.findAllByActive(true);
	}
	
	public List<Product> findAllActives(List<String> uuids) {
		return repository.findAllByActive(uuids, true);
	}
	
	public Optional<Product> findOne(String uuid) {
		return repository.findOneByUuidAndActive(uuid, true);
	}
	
	@Transactional
	public void saveAsync(RegistrationContract contract) {
		jmsTemplate.convertAndSend("products.register", contract);
	}
	
}
