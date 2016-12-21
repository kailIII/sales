package com.github.leosilvadev.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.github.leosilvadev.product.domain.Product;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

	public List<Product> findAllByActive(Boolean active);

	@Query("SELECT p FROM Product p WHERE p.uuid IN ?1 AND p.active = ?2")
	public List<Product> findAllByActive(List<String> uuids, Boolean active);

	public Optional<Product> findOneByUuidAndActive(String uuid, Boolean active);
	
}
