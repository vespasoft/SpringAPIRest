package com.springapirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springapirest.model.City;
import com.springapirest.model.Country;

@Repository
public interface CityRepository extends JpaRepository<City, Integer>  {
	List<City> findByCountry(Country country);
}
