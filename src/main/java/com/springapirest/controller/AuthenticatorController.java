package com.springapirest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springapirest.model.User;
import com.springapirest.service.UserServiceImpl;

/**
 * Created by Luigi Vespa on 28/05/18.
 */
@RestController
@RequestMapping("/api")
public class AuthenticatorController {
	
	UserServiceImpl userServiceImpl;
	
	@PostMapping("/users/authenticator")
    public User logIn(@RequestBody User user) {
		return userServiceImpl.getUserByUsername(user.getUsername());
    }

}
