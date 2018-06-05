package com.springapirest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="city")
public class City {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer id;
	 
	 @Column(name="name", length=45)
	 private String name;
	 
	 @ManyToOne(optional = false)
	 @JoinColumn(name="country_id", nullable=false)
	 private Country country;
	 
	 public City() {
	 }

	 public City(Country country) {
	    this.country = country;
	 }
	 
	 public City(Country country, String name) {
	   this.country = country;
	   this.name = name;
	 }
	
	 public City(Integer id, String name, Country country) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
	}

	public Integer getId() {
	    return this.id;
	}
	
	public void setId(Integer id) {
	    this.id = id;
	}
	
	public Country getCountry() {
	    return this.country;
	}
	
	public void setCountry(Country country) {
	    this.country = country;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

}


