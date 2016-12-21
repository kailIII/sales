package com.github.leosilvadev.invoice.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.leosilvadev.invoice.domain.Invoice;
import com.github.leosilvadev.invoice.domain.InvoiceProduct;
import com.github.leosilvadev.invoice.v1.contract.RegistrationContract;
import com.github.leosilvadev.invoice.v1.contract.RegistrationProduct;
import com.github.leosilvadev.issuer.domain.Issuer;
import com.github.leosilvadev.issuer.exceptions.InvalidIssuerException;
import com.github.leosilvadev.issuer.service.IssuerService;
import com.github.leosilvadev.product.service.ProductService;

@Service
public class InvoiceBuilder {

	private IssuerService issuerService;
	private ProductService productService;
	
	@Autowired
	public InvoiceBuilder(IssuerService issuerService, ProductService productService) {
		this.issuerService = issuerService;
		this.productService = productService;
	}
	
	public Invoice build(RegistrationContract contract) {
		List<String> productsId = contract.getProducts().stream()
				.map(RegistrationProduct::getProduct)
				.collect(Collectors.toList());
		
		Optional<Issuer> issuer = issuerService.findOne(contract.getDocumentNumber());
		if (!issuer.isPresent())
			throw new InvalidIssuerException();
		
		Set<InvoiceProduct> invoiceProducts = productService.findAllActives(productsId)
				.stream()
				.map(product -> {
					Integer quantity = getProductQuantity(product.getUuid(), contract.getProducts());
					return new InvoiceProduct(product, quantity);
				})
				.collect(Collectors.toSet());
		
		return new Invoice(issuer.get(), invoiceProducts);
	}
	
	private Integer getProductQuantity(String productUuid, List<RegistrationProduct> products) {
		List<RegistrationProduct> found = products.stream().filter(prod -> prod.getProduct().equals(productUuid)).collect(Collectors.toList());
		if (found.isEmpty()) {
			return 0;
		}
		return found.get(0).getQuantity();
	}
	
}
