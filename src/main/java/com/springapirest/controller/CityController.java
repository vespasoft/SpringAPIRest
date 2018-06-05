package com.springapirest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springapirest.exception.ResourceNotFoundException;
import com.springapirest.model.City;
import com.springapirest.model.Country;
import com.springapirest.model.User;
import com.springapirest.repository.CityRepository;
import com.springapirest.repository.CountryRepository;
import com.springapirest.service.CityServiceImpl;
import com.springapirest.service.CountryServiceImpl;

import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by luis vespa on 27/04/18.
 */
@RestController
@RequestMapping("/api")
@SwaggerDefinition(tags = {@Tag(name = "City Controller", description = "Operations pertaining of cities")})
public class CityController {
    
    @Autowired
	CityServiceImpl cityServiceImpl;

    public CityController(CityRepository cityRepository) {
    	this.cityServiceImpl = new CityServiceImpl(cityRepository);
	}
    
    @GetMapping("/cities/{id}")
    public City getCityById(@PathVariable(value = "id") int cityId) {
        return cityServiceImpl.getCityById(cityId);
    }

    @PostMapping("/cities")
    public ResponseEntity<City> createCity(@Valid @RequestBody City city) {
    	cityServiceImpl.createCity(city);
    	return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/cities/{id}")
    public ResponseEntity<City> updateCity(@PathVariable(value = "id") int cityId,
                                           @Valid @RequestBody City cityDetails) {
    	cityDetails.setId(cityId);
    	City updatedCity = cityServiceImpl.updateCity(cityDetails);
    	if (updatedCity==null) 
    		return ResponseEntity.badRequest().build();
    	else
    		return ResponseEntity.ok(updatedCity);
    }

    @DeleteMapping("/cities/{id}")
    public ResponseEntity<City> deleteCity(@PathVariable(value = "id") int cityId) {

    	cityServiceImpl.deleteCity(cityId);
        
        return ResponseEntity.ok().build();
    }
}