package com.springapirest.unit;

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
import com.springapirest.model.Category;
import com.springapirest.repository.CategoryRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = { CategoryController.class }, secure = false)
public class CategoryControllerTest {

	@Configuration
    static class ContextConfiguration {

        @Bean
        public CategoryController categoryController(CategoryRepository categoryRepository) {
        	CategoryController categoryController = new CategoryController(categoryRepository);
            return categoryController;
        }
    }	
	
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CategoryController categoryController;
    
    @Test
	public void getAllCategories() throws Exception {
    	
    	Category category = new Category(1, "Manicura y Pedicura");
		
		List<Category> allCategories = singletonList(category);
		
		given(categoryController.getAllCategories()).willReturn(allCategories);

		mvc.perform(get("/api/categories")
	               .contentType(APPLICATION_JSON))
	               .andExpect(status().isOk())
	               .andExpect(jsonPath("$[0].id").exists())
				   .andExpect(jsonPath("$[0].title", is(category.getTitle())));
		
	}
    
    @Test
	public void getCategoryById() throws Exception {
    	
    	Category category = new Category(1, "Manicura y Pedicura");
		
		given(categoryController.getCategoryById(category.getId())).willReturn(category);

		mvc.perform(get("/api/categories/"+category.getId())
	               .contentType(APPLICATION_JSON))
	               .andExpect(status().isOk())
	               .andExpect(jsonPath("id").exists())
				   .andExpect(jsonPath("title", is(category.getTitle())));
		
	}

}
