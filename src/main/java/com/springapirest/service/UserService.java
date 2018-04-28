package com.springapirest.service;

import java.util.List;
import java.util.Optional;

import com.springapirest.model.User;

public interface UserService {
	
	public List<User> getAll();
	
	public User getUserById(int id);
	
	public User getUserByUsername(String username);
	
	public void createUser(User user);
	
	public User updateUser(User userDetails);
	
	public void deleteUser(int id);

}
