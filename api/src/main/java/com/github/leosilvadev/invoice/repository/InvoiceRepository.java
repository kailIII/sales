package com.github.leosilvadev.invoice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.github.leosilvadev.invoice.domain.Invoice;

@Repository
public interface InvoiceRepository extends PagingAndSortingRepository<Invoice, Long> {

	@Query("select i from Invoice i where i.issuer.documentNumber = ?1")
	public List<Invoice> findAllByIssuer(String documentNumber);

	@Query(" SELECT i FROM Invoice i " +
		   " JOIN i.invoiceProducts ip " +
		   " JOIN ip.product p " +
		   " WHERE ip.product.uuid IN ?1")
	public List<Invoice> findAllThatContainsProducts(List<String> products);
	
	public List<Invoice> findAll();
	
}
