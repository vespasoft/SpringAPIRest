package com.springapirest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springapirest.exception.ResourceNotFoundException;
import com.springapirest.model.City;
import com.springapirest.model.Country;
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
@SwaggerDefinition(tags = {@Tag(name = "Country Controller", description = "Operations pertaining of countries and cities")})
public class CountryController {
    
    @Autowired
	CountryServiceImpl countryServiceImpl;
    
    @Autowired
	CityServiceImpl cityServiceImpl;

    public CountryController(CountryRepository countryRepository, CityRepository cityRepository) {
    	this.countryServiceImpl = new CountryServiceImpl(countryRepository);
    	this.cityServiceImpl = new CityServiceImpl(cityRepository);
	}

    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        return countryServiceImpl.getAll();
    }
    
    @GetMapping("/countries/{id}/cities")
    public List<City> getAllCitiesByCountry(@PathVariable(value = "id") int countryId) {
    	Country countryFinded = countryServiceImpl.getCountryById(countryId);
    	if (countryFinded==null) 
    		throw new ResourceNotFoundException("Countries", "id not found with "+ countryId);
    	    		
    	return cityServiceImpl.getAllByCountry(countryFinded);
    }
    
    @GetMapping("/countries/{id}")
    public Country getCountryById(@PathVariable(value = "id") int countryId) {
        return countryServiceImpl.getCountryById(countryId);
    }

    @PostMapping("/countries")
    public void createCountry(@Valid @RequestBody Country country) {
        countryServiceImpl.createCountry(country);
    }

    @PutMapping("/countries/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable(value = "id") int countryId,
                                           @Valid @RequestBody Country countryDetails) {
    	countryDetails.setId(countryId);
    	Country updatedCountry = countryServiceImpl.updateCountry(countryDetails);
    	if (updatedCountry==null) 
    		return ResponseEntity.badRequest().build();
    	else
    		return ResponseEntity.ok(updatedCountry);
    }

    @DeleteMapping("/countries/{id}")
    public ResponseEntity<Country> deleteCountry(@PathVariable(value = "id") int countryId) {

        countryServiceImpl.deleteCountry(countryId);
        
        return ResponseEntity.ok().build();
    }
}