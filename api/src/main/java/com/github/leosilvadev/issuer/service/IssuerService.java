package com.github.leosilvadev.issuer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.leosilvadev.issuer.domain.Issuer;
import com.github.leosilvadev.issuer.repository.IssuerRepository;

@Service
public class IssuerService {

	private IssuerRepository repository;
	
	@Autowired
	public IssuerService(IssuerRepository repository) {
		this.repository = repository;
	}
	
	public Optional<Issuer> findOne(String documentNumber) {
		return repository.findOneByDocumentNumber(documentNumber);
	}
	
}
