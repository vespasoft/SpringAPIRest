package com.springapirest.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springapirest.model.Product;
import com.springapirest.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
    
	public ProductServiceImpl(ProductRepository productRepository) {
			super();
			this.productRepository = productRepository;
	}

	@Override
	public List<Product> getAll() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	@Override
	public Product getServiceById(int id) {
		// TODO Auto-generated method stub
		Product product = productRepository.findOne(id);
		return product;
	}

}
