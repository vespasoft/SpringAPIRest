package com.springapirest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springapirest.model.Country;
import com.springapirest.repository.CountryRepository;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by luis vespa on 27/06/17.
 */
@RestController
@RequestMapping("/api")
public class CountryController {

    @Autowired
    CountryRepository countryRepository;

    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @PostMapping("/countries")
    public Country createCountry(@Valid @RequestBody Country Country) {
        return countryRepository.save(Country);
    }

    @GetMapping("/countries/{id}")
    public Country getCountryById(@PathVariable(value = "id") int CountryId) {
        return countryRepository.findOne(CountryId);
    }

    @PutMapping("/countries/{id}")
    public Country updateCountry(@PathVariable(value = "id") int CountryId,
                                           @Valid @RequestBody Country CountryDetails) {

        Country Country = countryRepository.findOne(CountryId);

        Country.setName(CountryDetails.getName());
        Country.setNicename(CountryDetails.getNicename());
        Country.setNumcode(CountryDetails.getNumcode());
        
        Country updatedCountry = countryRepository.save(Country);
        return updatedCountry;
    }

    @DeleteMapping("/countries/{id}")
    public ResponseEntity<?> deleteCountry(@PathVariable(value = "id") int CountryId) {
        Country Country = countryRepository.findOne(CountryId);

        countryRepository.delete(Country);

        return ResponseEntity.ok().build();
    }
}