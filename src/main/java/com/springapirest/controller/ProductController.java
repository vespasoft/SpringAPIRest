package com.springapirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springapirest.model.Product;
import com.springapirest.repository.ProductRepository;
import com.springapirest.service.ProductServiceImpl;


/**
 * Created by luis vespa on 06/05/18.
 */
@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	ProductServiceImpl productServiceImpl;

	public ProductController(ProductRepository productRepository) {
    	this.productServiceImpl = new ProductServiceImpl(productRepository);
	}
	
	@GetMapping("/products")
    public List<Product> getAllProducts() {
        return productServiceImpl.getAll();
    }
	
	@GetMapping("/products/{id}")
    public Product getProductById(@PathVariable(value = "id") int productId) {
        return productServiceImpl.getProductById(productId);
    }
	
}
