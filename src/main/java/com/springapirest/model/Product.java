package com.springapirest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by luis vespa on 30/04/18.
 */
@Entity
@Table(name = "product")
public class Product {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer id;
	 
	 @Column(name="title", length=45)
	 private String title;
	 
	 @NotBlank
	 @Column(name="description")
	 private String description;
	 
	 @Column(name="price", precision=22, scale=0)
	 private double price;

	 @Column(name="image")
	 private String image;
	 
	 @ManyToOne(optional = false)
	 @JoinColumn(name="category_id", nullable=false)
	 private Category category;
	 
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	 
}
