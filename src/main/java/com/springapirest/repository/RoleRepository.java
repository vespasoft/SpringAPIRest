package com.springapirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springapirest.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>   {

}
