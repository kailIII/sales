package com.github.leosilvadev.invoice.service

import spock.lang.Specification

import com.github.leosilvadev.invoice.domain.Invoice
import com.github.leosilvadev.invoice.v1.contract.RegistrationContract
import com.github.leosilvadev.invoice.v1.contract.RegistrationProduct
import com.github.leosilvadev.issuer.domain.Issuer
import com.github.leosilvadev.issuer.service.IssuerService
import com.github.leosilvadev.product.domain.Product
import com.github.leosilvadev.product.service.ProductService

class InvoiceBuilderSpec extends Specification {
	
	InvoiceBuilder builder
	
	def 'Should build an Invoice from a Registration Contract'() {
		setup:
		def issuerService = Mock(IssuerService)
		def productService = Mock(ProductService)
		builder = new InvoiceBuilder(issuerService, productService)
		
		and:
		def prod1 = new RegistrationProduct(product: 'prod1', quantity: 1)
		def prod2 = new RegistrationProduct(product: 'prod2', quantity: 5)
		def prod3 = new RegistrationProduct(product: 'prod3', quantity: 10)
		def contract = new RegistrationContract(documentNumber: '1234', products: [prod1, prod2, prod3])
		
		and:
		1 * issuerService.findOne('1234') >> Optional.of(new Issuer(name: 'Issuer'))
		
		and:
		1 * productService.findAllActives(['prod1', 'prod2', 'prod3']) >> [
			new Product(uuid: 'prod1', price: 10),
			new Product(uuid: 'prod2', price: 20),
			new Product(uuid: 'prod3', price: 30)
		]
		
		when:
		Invoice invoice = builder.build(contract)
		
		then:
		invoice.issuer.name == 'Issuer'
		invoice.invoiceProducts.size() == 3
	}

}
