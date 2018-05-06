package com.springapirest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by luis vespa on 06/05/18.
 */
@Entity
@Table(name = "category")
public class Category {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer id;
	 
	 @Column(name="title", length=45)
	 private String title;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	 
}
