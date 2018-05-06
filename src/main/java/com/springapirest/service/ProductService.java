package com.springapirest.service;

import java.util.List;

import com.springapirest.model.Product;


public interface ProductService {
	
	public List<Product> getAll();
	
	public Product getProductById(int id);
	
}
