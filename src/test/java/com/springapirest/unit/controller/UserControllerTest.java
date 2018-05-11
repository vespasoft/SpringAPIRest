package com.springapirest.unit.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.springapirest.controller.CategoryController;
import com.springapirest.controller.UserController;
import com.springapirest.model.City;
import com.springapirest.model.User;
import com.springapirest.repository.RoleRepository;
import com.springapirest.repository.UserRepository;
import com.springapirest.service.TempTokenServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = { CategoryController.class }, secure = false)
public class UserControllerTest {
	
	@Configuration
    static class ContextConfiguration {

        @Bean
        public UserController userController(UserRepository userRepository,
				  RoleRepository roleRepository,	
                  BCryptPasswordEncoder bCryptPasswordEncoder, TempTokenServiceImpl tempTokenService) {
        	UserController userController = new UserController(userRepository, roleRepository, bCryptPasswordEncoder, tempTokenService);
            return userController;
        }
    }
	
	@Autowired
    private MockMvc mvc;

    @MockBean
    private UserController userController;
    
    private ObjectMapper mapper;
    
    @Before
    public void setUp() {
        mapper = new ObjectMapper();
    }
	
    @Test
	public void signUp() throws Exception {
		
		ObjectNode cityJson = mapper.createObjectNode();
	    cityJson.put("id", "1");
	    cityJson.put("city", "MADRID");
	    
		ObjectNode userJson = mapper.createObjectNode();
	    userJson.put("name", "jonn");
	    userJson.put("surname", "benitez");
	    userJson.put("username", "test@gmail.com");
	    userJson.put("password", "password");
	    userJson.put("phone", "666659054");
	    userJson.put("city", cityJson);
	    
	    mvc.perform(post("/api/users")
               .contentType(APPLICATION_JSON)
               .content(mapper.writeValueAsBytes(userJson))
               )
               .andExpect(status().isOk());
	}

}
