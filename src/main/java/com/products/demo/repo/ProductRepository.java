package com.products.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.products.demo.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByCategory(String category);
}
