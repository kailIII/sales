package com.github.leosilvadev.product.v1.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.leosilvadev.product.domain.Product;
import com.github.leosilvadev.product.service.ProductService;
import com.github.leosilvadev.product.v1.contract.RegistrationContract;

@RestController
@RequestMapping("/v1/products")
@CrossOrigin(origins = "*")
public class ProductController {

	private ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping
	public ResponseEntity<?> list() {
		List<Product> products = productService.findAllActives();
		if (products.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(products);
	}
	
	@GetMapping("/{uuid}")
	public ResponseEntity<?> findOne(@PathVariable String uuid) {
		Optional<Product> product = productService.findOne(uuid);
		if (product.isPresent()) {
			return ResponseEntity.ok(product.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> register(@RequestBody @Valid RegistrationContract contract) {
		productService.saveAsync(contract);
		return ResponseEntity.accepted().body(contract);
	}
	
}
