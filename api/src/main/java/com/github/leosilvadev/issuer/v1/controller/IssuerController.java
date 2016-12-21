package com.github.leosilvadev.issuer.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.leosilvadev.issuer.domain.Issuer;
import com.github.leosilvadev.issuer.repository.IssuerRepository;
import com.github.leosilvadev.issuer.v1.contract.RegistrationContract;

@RestController
@RequestMapping("/v1/issuers")
public class IssuerController {
	
	@Autowired
	private IssuerRepository issuerRepository;

	@PostMapping
	public ResponseEntity<?> register(@RequestBody @Valid RegistrationContract contract) {
		Issuer issuer = contract.asIssuer();
		issuerRepository.save(issuer);
		return ResponseEntity.ok(issuer);
	}
	
	@GetMapping
	public ResponseEntity<?> list() {
		return ResponseEntity.ok(issuerRepository.findAll());
	}
	
}
