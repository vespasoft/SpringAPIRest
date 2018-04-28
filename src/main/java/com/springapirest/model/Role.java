package com.springapirest.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.springapirest.model.User;

/**
 * Created by Luigi Vespa on 27/06/18.
 */
@Entity
@Table(name = "role")
public class Role {
  
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
 
    private String name;
    
	@ManyToMany(mappedBy = "roles")
    private Collection<User> users;
	
	@ManyToMany
    @JoinTable(
        name = "roles_privileges", 
        joinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(
          name = "privilege_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges;  

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
 
}
