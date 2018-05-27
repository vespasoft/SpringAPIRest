package com.springapirest.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springapirest.model.Country;
import com.springapirest.repository.CountryRepository;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryRepository countryRepository;
    
	public CountryServiceImpl(CountryRepository countryRepository) {
			super();
			this.countryRepository = countryRepository;
	}

	@Override
	public List<Country> getAll() {
		return countryRepository.findAll();
	}

	@Override
	public Country getCountryById(int id) {
		return countryRepository.findOne(id);
	}
	
	@Override
	public void createCountry(Country country) {
		countryRepository.save(country);
	}

	@Override
	public Country updateCountry(Country countryDetails) {
		Country country = countryRepository.findOne(countryDetails.getId());
		if (country==null) 
			return null;
		else {
			country.setName(countryDetails.getName());
	        country.setNicename(countryDetails.getNicename());
	        country.setNumcode(countryDetails.getNumcode());
	        
	        return countryRepository.save(country);
		}
	}

	@Override
	public void deleteCountry(int id) {
		Country country = countryRepository.findOne(id);

        countryRepository.delete(country);
	}

}
