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

}
