package com.springapirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springapirest.exception.ResourceNotFoundException;
import com.springapirest.model.Category;
import com.springapirest.model.Product;
import com.springapirest.repository.CategoryRepository;
import com.springapirest.repository.ProductRepository;
import com.springapirest.service.CategoryServiceImpl;
import com.springapirest.service.ProductServiceImpl;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;


/**
 * Created by luis vespa on 06/05/18.
 */
@RestController
@RequestMapping("/api")
@SwaggerDefinition(tags = {@Tag(name = "Products Controller", description = "Operations pertaining to products of a User Loggded")})
public class ProductController {
	
	@Autowired
	ProductServiceImpl productServiceImpl;
	
	@Autowired
	CategoryServiceImpl categoryServiceImpl;

	public ProductController(ProductRepository productRepository, CategoryRepository categoryRepository) {
    	this.productServiceImpl = new ProductServiceImpl(productRepository);
    	this.categoryServiceImpl = new CategoryServiceImpl(categoryRepository);
	}
	
	@GetMapping("/products")
    public List<Product> getAllProducts() {
        return productServiceImpl.getAll();
    }
	
	@GetMapping("/products/{id}")
    public Product getProductById(@PathVariable(value = "id") int productId) {
        Product productFinded = productServiceImpl.getProductById(productId);
        if (productFinded==null) 
    		throw new ResourceNotFoundException("Product", "id not found with "+ productId);
        
        return productFinded;
    }
	
	@GetMapping("/categories/{id}/products")
    public List<Product> getAllByCategory(@PathVariable(value = "id") int categoryId) {
        Category categoryFinded = categoryServiceImpl.getCategoryById(categoryId);
        if (categoryFinded==null) 
    		throw new ResourceNotFoundException("Category", "id not found with "+ categoryId);
        
        return productServiceImpl.getAllByCategory(categoryFinded);
    }
	
}
