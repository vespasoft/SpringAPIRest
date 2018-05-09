package com.springapirest.unit.controller;

import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.springapirest.controller.CategoryController;
import com.springapirest.controller.ProductController;
import com.springapirest.model.Category;
import com.springapirest.model.Product;
import com.springapirest.repository.CategoryRepository;
import com.springapirest.repository.ProductRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = { CategoryController.class }, secure = false)
public class ProductControllerTest {

	@Configuration
    static class ContextConfiguration {

        @Bean
        public ProductController categoryController(ProductRepository productRepository, CategoryRepository categoryRepository) {
        	ProductController productController = new ProductController(productRepository, categoryRepository);
            return productController;
        }
    }	
	
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductController productController;
    
    @Test
	public void getAllProducts() throws Exception {
    	
    	Product product = new Product();
    	product.setId(1);
		
		List<Product> allProducts = singletonList(product);
		
		given(productController.getAllProducts()).willReturn(allProducts);

		mvc.perform(get("/api/products")
	               .contentType(APPLICATION_JSON))
	               .andExpect(status().isOk())
	               .andExpect(jsonPath("$[0].id").exists());
		
	}
    
    @Test
	public void getProductById() throws Exception {
    	
    	Product product = new Product();
    	product.setId(1);
		
		given(productController.getProductById(product.getId())).willReturn(product);

		mvc.perform(get("/api/products/"+product.getId())
	               .contentType(APPLICATION_JSON))
	               .andExpect(status().isOk())
	               .andExpect(jsonPath("id").exists())
				   .andExpect(jsonPath("title", is(product.getTitle())));
		
	}
    
    @Test
	public void getAllProductsByCategory() throws Exception {
    	
    	Category category = new Category(3, "Peluquería");
		
    	Product product = new Product();
    	product.setId(1);
		
    	List<Product> allProducts = singletonList(product);
		
		given(productController.getAllByCategory(category.getId())).willReturn(allProducts);
		
		mvc.perform(get("/api/categories/"+category.getId()+"/products")
	               .contentType(APPLICATION_JSON))
	               .andExpect(status().isOk())
	               .andExpect(jsonPath("$[0].id").exists());
		
	}

}
