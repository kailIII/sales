package com.github.leosilvadev.invoice.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.github.leosilvadev.invoice.domain.Invoice;
import com.github.leosilvadev.invoice.repository.InvoiceRepository;
import com.github.leosilvadev.invoice.service.InvoiceBuilder;
import com.github.leosilvadev.invoice.v1.contract.RegistrationContract;

@Component
public class InvoiceRegistrationConsumer {

	private InvoiceRepository repository;
	private InvoiceBuilder invoiceBuilder;
	
	@Autowired
	public InvoiceRegistrationConsumer(InvoiceRepository repository, InvoiceBuilder invoiceBuilder) {
		this.repository = repository;
		this.invoiceBuilder = invoiceBuilder;
	}
	
	@JmsListener(destination = "invoices.register", containerFactory = "jmsListerContainerFactory")
    public void receiveMessage(RegistrationContract contract) {
		Invoice invoice = invoiceBuilder.build(contract);
        repository.save(invoice);
    }
	
}
