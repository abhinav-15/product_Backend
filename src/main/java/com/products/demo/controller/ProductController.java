package com.products.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.products.demo.entity.Product;
import com.products.demo.repo.ProductRepository;
@RestController
@RequestMapping("/api/products")
public class ProductController {
	 @Autowired
	    private ProductRepository productRepository;

	    @GetMapping()
	    public List<Product> getAllProducts() {
	        return productRepository.findAll();
	    }

	    @GetMapping("/{id}")
	    public Product getProductById(@PathVariable Long id) {
	        return productRepository.findById(id).orElse(null);
	    }

	    @GetMapping("/weird")
	    public List<Product> getWeirdCategoryProducts() {
	        return productRepository.findByCategory("weird");
	    }
	    
	    @GetMapping("/example")
	    public ResponseEntity<String> exampleEndpoint() {
	        String responseBody = "Hello, this is the response body!";
	        
	        // You can customize the status, headers, and body as needed
	        return ResponseEntity
	                .status(HttpStatus.OK)
	                .header("Custom-Header", "header-value")
	                .body(responseBody);
	    }

	    @PutMapping("/{id}/update-price")
	    public ResponseEntity<Product> updateProductPrice(@PathVariable Long id, @RequestParam Double newPrice) {
	        try {
	            Optional<Product> optionalProduct = productRepository.findById(id);
	            if (optionalProduct.isPresent()) {
	                Product product = optionalProduct.get();
	                product.setPrice(newPrice);
	                Product updatedProduct = productRepository.save(product);
	                return ResponseEntity.ok(updatedProduct);
	            } else {
	                // Product not found, return a 404 Not Found response
	                return ResponseEntity.notFound().build();
	            }
	        } catch (Exception e) {
	            // Log the exception details
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }


}
