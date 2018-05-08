package com.springapirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springapirest.model.Category;
import com.springapirest.model.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>  {
	List<Product> findAllByCategory(Category category);
}
