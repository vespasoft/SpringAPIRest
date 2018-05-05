package com.springapirest.service;

import java.util.List;
import com.springapirest.model.City;
import com.springapirest.model.Country;

public interface CityService {
	
	public List<City> getAllByCountry(Country country);

}
