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
import com.springapirest.model.City;
import com.springapirest.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CityIntegrationTest {
	
	@Autowired
    private TestRestTemplate restTemplate;
	
    private ObjectMapper mapper;
    
    @Before
    public void setUp() {
        mapper = new ObjectMapper();
    }
    
    @Test
    public void createCity() {
    	ObjectNode countryJson = mapper.createObjectNode();
    	countryJson.put("id", "199");
    	countryJson.put("name", "SPAIN");
	    
		ObjectNode cityJson = mapper.createObjectNode();
		cityJson.put("name", "Valencia");
	    cityJson.put("country", countryJson);
	    
        ResponseEntity<City> responseCity =
            restTemplate.postForEntity("/api/cities", cityJson, City.class);
        //User user = responseUser.getBody();
        assertEquals(HttpStatus.CREATED, responseCity.getStatusCode());
    }

}
