package com.springapirest.service;

import java.util.List;

import com.springapirest.model.Category;


public interface CategoryService {
	
	public List<Category> getAll();
	
	public Category getCategoryById(int id);
	
}
