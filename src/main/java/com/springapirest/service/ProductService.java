package com.springapirest.service;

import java.util.List;

import com.springapirest.model.Category;
import com.springapirest.model.Product;


public interface ProductService {
	
	public List<Product> getAll();
	
	public List<Product> getAllByCategory(Category category);
	
	public Product getProductById(int id);
	
}
