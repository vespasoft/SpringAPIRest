package com.springapirest.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springapirest.model.City;
import com.springapirest.model.Country;
import com.springapirest.repository.CityRepository;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepository cityRepository;
    
	public CityServiceImpl(CityRepository cityRepository) {
		super();
		this.cityRepository = cityRepository;
	}

	@Override
	public List<City> getAllByCountry(Country country) {
		return cityRepository.findByCountry(country);
	}

	@Override
	public City getCityById(int id) {
		return cityRepository.findOne(id);
	}

	@Override
	public void createCity(City city) {
		cityRepository.save(city);
		
	}

	@Override
	public City updateCity(City cityDetails) {
		City city = cityRepository.findOne(cityDetails.getId());
		if (city==null) 
			return null;
		else {
			city.setName(cityDetails.getName());
	        city.setCountry(cityDetails.getCountry());
	        
	        return cityRepository.save(city);
		}
	}

	@Override
	public void deleteCity(int id) {
		City city = cityRepository.findOne(id);

		cityRepository.delete(city);
		
	}

}
