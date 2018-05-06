package com.springapirest.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springapirest.model.Category;
import com.springapirest.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
    
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
			super();
			this.categoryRepository = categoryRepository;
	}

	@Override
	public List<Category> getAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Category getCategoryById(int id) {
		Category category = categoryRepository.findOne(id);
		return category;
	}

}
