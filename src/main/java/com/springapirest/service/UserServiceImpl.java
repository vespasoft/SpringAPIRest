package com.springapirest.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springapirest.model.User;
import com.springapirest.repository.RoleRepository;
import com.springapirest.repository.UserRepository;
import com.springapirest.thread.ThreadSendValidationCodeEmail;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	TempTokenServiceImpl tempTokenService;
	
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
	public UserServiceImpl(UserRepository userRepository, 
		RoleRepository roleRepository, 
		BCryptPasswordEncoder bCryptPasswordEncoder,
		TempTokenServiceImpl tempTokenService) {
			super();
			this.userRepository = userRepository;
			this.bCryptPasswordEncoder = bCryptPasswordEncoder;
			this.tempTokenService = tempTokenService;
			this.roleRepository = roleRepository;
	}

	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(int id) {
		return userRepository.findOne(id);
	}
	
	@Override
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public void createUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setCreatedAt(new Date());
        user.setRoles(Arrays.asList(roleRepository.findOne(1)));
        user.setVerified(false);
        user.setActive(true);
        userRepository.save(user);
        
        User userAdded = getUserByUsername(user.getUsername());
        if (userAdded!=null) {
        	String tokenGenerated = tempTokenService.createToken(userAdded);
        	
        	// ejecuta un thread (hilo) en 2do plan donde se envia el correo.
            ThreadSendValidationCodeEmail sendEmail = new ThreadSendValidationCodeEmail(user, tokenGenerated);
            sendEmail.start();
            
        }
        
	}

	@Override
	public User updateUser(User userDetails) {
		User user = userRepository.findOne(userDetails.getId());
        if (userDetails.getName()!=null) 
        	user.setName(userDetails.getName());
        if (userDetails.getSurname()!=null) 
        	user.setSurname(userDetails.getSurname());
        if (userDetails.getPhone()!=null) 
        	user.setPhone(userDetails.getPhone());
        user.setUpdatedAt(new Date());
        
        return userRepository.save(user);
	}

	@Override
	public void deleteUser(int id) {
		User user = userRepository.findOne(id);
        userRepository.delete(user);
	}

}
