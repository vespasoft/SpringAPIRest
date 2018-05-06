package com.springapirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springapirest.model.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>  {

}
