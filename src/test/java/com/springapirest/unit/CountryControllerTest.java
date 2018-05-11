package com.springapirest.unit;

import static org.hamcrest.CoreMatchers.is;
import java.util.List;
import static java.util.Collections.singletonList;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;
import com.springapirest.controller.CountryController;
import com.springapirest.model.City;
import com.springapirest.model.Country;
import com.springapirest.repository.CityRepository;
import com.springapirest.repository.CountryRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = { CountryController.class }, secure = false)
public class CountryControllerTest {
	
	@Configuration
    static class ContextConfiguration {

        // this bean will be injected into the OrderServiceTest class
        @Bean
        public CountryController countryController(CountryRepository countryRepository, CityRepository cityRepository) {
        	CountryController countryController = new CountryController(countryRepository, cityRepository);
            // set properties, etc.
            return countryController;
        }
    }	
	
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CountryController countryController;
	
	@Test
	public void getAllCountries() throws Exception {
		
		Country country = new Country(1, "AFGHANISTAN");
		
		List<Country> allCountries = singletonList(country);
		
		given(countryController.getAllCountries()).willReturn(allCountries);

		mvc.perform(get("/api/countries")
	               .contentType(APPLICATION_JSON))
	               .andExpect(status().isOk())
	               .andExpect(jsonPath("$[0].id").exists())
				   .andExpect(jsonPath("$[0].name", is(country.getName())));
		
	}

	@Test
	public void getCountryById() throws Exception {
		Country country = new Country(1, "AFGHANISTAN");
		
		given(countryController.getCountryById(country.getId())).willReturn(country);

		mvc.perform(get("/api/countries/"+country.getId())
	               .contentType(APPLICATION_JSON))
	               .andExpect(status().isOk())
	               .andExpect(jsonPath("id").exists())
				   .andExpect(jsonPath("name", is(country.getName())));
		
	}
	
	@Test
	public void getAllCitiesByCountry() throws Exception {
		Country country = new Country(199, "SPAIN");
		
		City city = new City(1, "MADRID", country);
		
		List<City> allCities = singletonList(city);
		
		given(countryController.getAllCitiesByCountry(country.getId())).willReturn(allCities);

		mvc.perform(get("/api/countries/"+country.getId()+"/cities")
	               .contentType(APPLICATION_JSON))
	               .andExpect(status().isOk())
	               .andExpect(jsonPath("$[0].id").exists())
				   .andExpect(jsonPath("$[0].city", is(city.getCity())));
		
	}
	
}
