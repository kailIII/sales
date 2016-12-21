package com.github.leosilvadev.invoice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.github.leosilvadev.invoice.domain.Invoice;
import com.github.leosilvadev.invoice.repository.InvoiceRepository;
import com.github.leosilvadev.invoice.v1.contract.RegistrationContract;

@Service
public class InvoiceService {

	private JmsTemplate jmsTemplate;
	private InvoiceRepository repository;
	
	@Autowired
	public InvoiceService(JmsTemplate jmsTemplate, InvoiceRepository repository) {
		this.jmsTemplate = jmsTemplate;
		this.repository = repository;
	}
	
	public List<Invoice> findAll(InvoiceFilter filter) {
		if (filter.hasIssuerDocumentNumber()) {
			return repository.findAllByIssuer(filter.getIssuerDocumentNumber());
			
		} else if (filter.hasProductsId()) {
			return repository.findAllThatContainsProducts(filter.getProductsId());
			
		}
		return repository.findAll();
	}
	
	public RegistrationContract saveAsync(RegistrationContract contract) {
		jmsTemplate.convertAndSend("invoices.register", contract);
		return contract;
	}
	
	public static class InvoiceFilter {

		private String issuerDocumentNumber;

		private List<String> productsId;
		
		public InvoiceFilter(String issuerDocumentNumber, List<String> productsId) {
			this.issuerDocumentNumber = issuerDocumentNumber;
			this.productsId = productsId;
		}

		public String getIssuerDocumentNumber() {
			return issuerDocumentNumber;
		}

		public List<String> getProductsId() {
			return productsId;
		}
		
		public Boolean hasIssuerDocumentNumber() {
			return issuerDocumentNumber != null && !issuerDocumentNumber.isEmpty();
		}
		
		public Boolean hasProductsId() {
			return productsId != null && !productsId.isEmpty();
		}
		
	}
	
}
