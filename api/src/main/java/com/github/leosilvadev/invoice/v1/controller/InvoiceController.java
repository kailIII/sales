package com.github.leosilvadev.invoice.v1.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.leosilvadev.invoice.domain.Invoice;
import com.github.leosilvadev.invoice.service.InvoiceService;
import com.github.leosilvadev.invoice.service.InvoiceService.InvoiceFilter;
import com.github.leosilvadev.invoice.v1.contract.RegistrationContract;
import com.github.leosilvadev.issuer.domain.Issuer;
import com.github.leosilvadev.issuer.exceptions.InvalidIssuerException;
import com.github.leosilvadev.issuer.service.IssuerService;

@RestController
@RequestMapping("/v1/invoices")
@CrossOrigin(origins = "*")
public class InvoiceController {

	private InvoiceService invoiceService;
	private IssuerService issuerService;
	
	@Autowired
	public InvoiceController(InvoiceService invoiceService, IssuerService issuerService) {
		this.invoiceService = invoiceService;
		this.issuerService = issuerService;
	}
	
	@GetMapping
	public ResponseEntity<?> list(
			@RequestParam(required=false, name="issuer") String issuerId,
			@RequestParam(required=false, name="product") List<String> productsId) {
		
		List<Invoice> invoices = invoiceService.findAll(new InvoiceFilter(issuerId, productsId));
		
		if (invoices.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(invoices);
	}
	
	@PostMapping
	public ResponseEntity<RegistrationContract> register(@RequestBody @Valid RegistrationContract contract) {
		Optional<Issuer> issuer = issuerService.findOne(contract.getDocumentNumber());
		if (issuer.isPresent()) {
			invoiceService.saveAsync(contract);
			return ResponseEntity.accepted().body(contract);
		}
		
		throw new InvalidIssuerException();
	}

}
