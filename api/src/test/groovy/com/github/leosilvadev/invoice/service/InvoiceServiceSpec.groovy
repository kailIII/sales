package com.github.leosilvadev.invoice.service

import spock.lang.Specification

import com.github.leosilvadev.invoice.repository.InvoiceRepository
import com.github.leosilvadev.invoice.service.InvoiceService.InvoiceFilter

class InvoiceServiceSpec extends Specification {
	
	InvoiceService service
	
	def 'Should find all invoices by issuer'() {
		setup:
		def repository = Mock(InvoiceRepository)
		service = new InvoiceService(null, repository)
		
		and:
		def documentNumber = '1234'
		
		and:
		def filter = new InvoiceFilter(documentNumber, [])
		
		when:
		service.findAll(filter)
		
		then:
		1 * repository.findAllByIssuer(documentNumber)
	}
	
	def 'Should find all invoices that contains products'() {
		setup:
		def repository = Mock(InvoiceRepository)
		service = new InvoiceService(null, repository)
		
		and:
		def products = ['1234', '5678']
		
		and:
		def filter = new InvoiceFilter(null, products)
		
		when:
		service.findAll(filter)
		
		then:
		1 * repository.findAllThatContainsProducts(products)
	}

}
