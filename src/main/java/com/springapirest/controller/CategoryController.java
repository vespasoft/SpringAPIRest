package com.springapirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springapirest.exception.ResourceNotFoundException;
import com.springapirest.model.Category;
import com.springapirest.repository.CategoryRepository;
import com.springapirest.service.CategoryServiceImpl;

import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;


/**
 * Created by luis vespa on 06/05/18.
 */
@RestController
@RequestMapping("/api")
@SwaggerDefinition(tags = {@Tag(name = "Category Controller", description = "Operations pertaining of product categories")})
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
		Category categoryFinded = categoryServiceImpl.getCategoryById(categoryId);
        if (categoryFinded==null) 
    		throw new ResourceNotFoundException("Category", "id", categoryId);
        
        return categoryFinded;
    }
	
}
