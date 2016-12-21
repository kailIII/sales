package com.github.leosilvadev.issuer.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.github.leosilvadev.issuer.domain.Issuer;

@Repository
public interface IssuerRepository extends PagingAndSortingRepository<Issuer, Long> {

	public Optional<Issuer> findOneByDocumentNumber(String documentNumber);
	
}
