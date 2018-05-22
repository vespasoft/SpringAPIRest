package com.springapirest.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springapirest.exception.ResourceNotFoundException;
import com.springapirest.model.TempToken;
import com.springapirest.model.User;
import com.springapirest.repository.RoleRepository;
import com.springapirest.repository.TempTokenRepository;
import com.springapirest.repository.UserRepository;
import com.springapirest.security.TokenAuthenticationManager;
import com.springapirest.service.TempTokenServiceImpl;
import com.springapirest.service.UserServiceImpl;
import com.springapirest.thread.ThreadSendValidationCodeEmail;
import com.springapirest.thread.ThreadSendWelcomeEmail;

/**
 * Created by Luigi Vespa on 28/05/18.
 */
@RestController
@RequestMapping("/api")
public class VerificatorController {
	
	@Autowired
	TempTokenServiceImpl tempTokenServiceImpl;
	
	@Autowired
	UserServiceImpl userServiceImpl;

    public VerificatorController(UserRepository userRepository,
    					  RoleRepository roleRepository,
    					  TempTokenRepository tokenRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder, 
                          TempTokenServiceImpl tempTokenService) {
        this.userServiceImpl = new UserServiceImpl(userRepository, roleRepository, bCryptPasswordEncoder, tempTokenService);
        this.tempTokenServiceImpl = new TempTokenServiceImpl(tokenRepository);
    }
	
	@GetMapping("/verificator")
    public void generateTempToken(HttpServletRequest request) {
    	UsernamePasswordAuthenticationToken authentication = TokenAuthenticationManager.getAuthentication(request);
    	User userFinded = userServiceImpl.getUserByUsername(authentication.getName());
    	System.out.println("user "+userFinded.getUsername());
    	tempTokenServiceImpl.createToken(userFinded);
    }
	
	@PostMapping("/verificator")
    public void verificator(HttpServletRequest request, @RequestBody TempToken token) {
		UsernamePasswordAuthenticationToken authentication = TokenAuthenticationManager.getAuthentication(request);
    	User userFinded = userServiceImpl.getUserByUsername(authentication.getName());
    	System.out.println("user "+userFinded.getUsername());
    	
		TempToken tempToken = tempTokenServiceImpl.getTempTokenByUser(userFinded);
		if (tempToken==null) 
    		throw new ResourceNotFoundException("Verificator", "This user have not token generated.");
		
		if (tempToken.getTokenString().equals(token.getTokenString())) {
			userFinded.setVerified(true);
			userServiceImpl.updateUser(userFinded);
			tempTokenServiceImpl.deleteToken(userFinded);
			// ejecuta un thread (hilo) en 2do plano donde se envia el correo.
            ThreadSendWelcomeEmail sendEmail = new ThreadSendWelcomeEmail(userFinded);
            sendEmail.start();
		} else {
			throw new ResourceNotFoundException("Verificator", "This token is not valid");
		}
		
    }

}
