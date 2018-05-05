package com.springapirest.service;

import java.util.List;
import com.springapirest.model.Country;

public interface CountryService {
	
	public List<Country> getAll();
	
	public Country getCountryById(int id);
	
	public void createCountry(Country country);
	
	public Country updateCountry(Country countryDetails);
	
	public void deleteCountry(int id);

}
