package com.springapirest.service;

import com.springapirest.model.TempToken;
import com.springapirest.model.User;

public interface TempTokenService {
	
	public TempToken getTempTokenByUser(User user);
	
	public String createToken(User user);
	
	public void deleteToken(User user);
	
}
