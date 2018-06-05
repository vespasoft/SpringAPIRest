package com.springapirest.service;

import java.util.List;
import com.springapirest.model.City;
import com.springapirest.model.Country;

public interface CityService {
	
	public City getCityById(int id);
	
	public void createCity(City city);
	
	public City updateCity(City cityDetails);
	
	public void deleteCity(int id);
	
	public List<City> getAllByCountry(Country country);

}
