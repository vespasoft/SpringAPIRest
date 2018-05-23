package com.springapirest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springapirest.model.User;
import com.springapirest.repository.RoleRepository;
import com.springapirest.repository.UserRepository;
import com.springapirest.security.TokenAuthenticationManager;
import com.springapirest.service.TempTokenServiceImpl;
import com.springapirest.service.UserServiceImpl;

import io.swagger.annotations.Api;

/**
 * Created by Luigi Vespa on 27/06/18.
 */
@RestController
@RequestMapping("/api")
@Api(value = "User Controller", 
description = "Operations pertaining of User Logged")
public class UserController {

	@Autowired
	UserServiceImpl userServiceImpl;

    public UserController(UserRepository userRepository,
    					  RoleRepository roleRepository,	
                          BCryptPasswordEncoder bCryptPasswordEncoder, 
                          TempTokenServiceImpl tempTokenService) {
        this.userServiceImpl = new UserServiceImpl(userRepository, roleRepository, bCryptPasswordEncoder, tempTokenService);
    }

    @GetMapping("/users")
    public User getUserById(HttpServletRequest request) {
    	UsernamePasswordAuthenticationToken authentication = TokenAuthenticationManager.getAuthentication(request);
    	User user = userServiceImpl.getUserByUsername(authentication.getName());
    	return user;
    }

    @PostMapping("/users")
    public ResponseEntity<User> signUp(@Valid @RequestBody User user) {
        userServiceImpl.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    @PutMapping("/users")
    public User updateUser(HttpServletRequest request,
                                          @RequestBody User userDetails) {
    	UsernamePasswordAuthenticationToken authentication = TokenAuthenticationManager.getAuthentication(request);
  
    	User user = userServiceImpl.getUserByUsername(authentication.getName());
    	
    	userDetails.setId(user.getId());
        
        return userServiceImpl.updateUser(userDetails);
    }

    @DeleteMapping("/users")
    public ResponseEntity<?> deleteUser(HttpServletRequest request) {
    	UsernamePasswordAuthenticationToken authentication = TokenAuthenticationManager.getAuthentication(request);
    	
    	User user = userServiceImpl.getUserByUsername(authentication.getName());
    	userServiceImpl.deleteUser(user.getId());

        return ResponseEntity.ok().build();
    }
    
}
