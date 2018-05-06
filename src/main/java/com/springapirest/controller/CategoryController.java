package com.springapirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springapirest.model.Category;
import com.springapirest.repository.CategoryRepository;
import com.springapirest.service.CategoryServiceImpl;


/**
 * Created by luis vespa on 06/05/18.
 */
@RestController
@RequestMapping("/api")
public class CategoryController {
	
	@Autowired
	CategoryServiceImpl categoryServiceImpl;

	public CategoryController(CategoryRepository categoryRepository) {
    	this.categoryServiceImpl = new CategoryServiceImpl(categoryRepository);
	}
	
	@GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryServiceImpl.getAll();
    }
	
	@GetMapping("/categories/{id}")
    public Category getCategoryById(@PathVariable(value = "id") int categoryId) {
        return categoryServiceImpl.getCategoryById(categoryId);
    }
	
}
