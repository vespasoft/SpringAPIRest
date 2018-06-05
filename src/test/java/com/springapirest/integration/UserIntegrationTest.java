package com.springapirest.integration;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.springapirest.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserIntegrationTest {

	@Autowired
    private TestRestTemplate restTemplate;
	
    private ObjectMapper mapper;
    
    @Before
    public void setUp() {
        mapper = new ObjectMapper();
    }
	
    @Test
    public void createUser() {
    	ObjectNode cityJson = mapper.createObjectNode();
	    cityJson.put("id", "1");
	    cityJson.put("city", "MADRID");
	    
		ObjectNode userJson = mapper.createObjectNode();
	    userJson.put("name", "jonn");
	    userJson.put("surname", "benitez");
	    userJson.put("username", "test1234@gmail.com");
	    userJson.put("password", "password");
	    userJson.put("phone", "666659054");
	    userJson.put("city", cityJson);
	    
        ResponseEntity<User> responseUser =
            restTemplate.postForEntity("/api/users", userJson, User.class);
        //User user = responseUser.getBody();
        assertEquals(HttpStatus.CREATED, responseUser.getStatusCode());
    }

}
