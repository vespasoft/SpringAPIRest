package com.springapirest.service;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springapirest.model.TempToken;
import com.springapirest.model.User;
import com.springapirest.repository.TempTokenRepository;
import com.springapirest.util.StringUtil;

@Service
public class TempTokenServiceImpl implements TempTokenService {

	@Autowired
	private TempTokenRepository tokenRepository;
    
	public TempTokenServiceImpl(TempTokenRepository tokenRepository) {
		super();
		this.tokenRepository = tokenRepository;
	}

	@Override
	public TempToken getTempTokenByUser(User user) {
		return tokenRepository.findByUser(user);
	}

	@Override
	public String createToken(User user) {
	    TempToken tokenFinded = tokenRepository.findByUser(user);
	    String tokenGenerated = StringUtil.generateTokenString(4);
	    if (tokenFinded==null) {
	    	TempToken token = new TempToken();
			token.setCreatedAt(new Date());
			token.setExpirationAt(new Date());
			token.setTokenString(tokenGenerated);
			token.setUser(user);
			tokenRepository.save(token);
	    } else {
	    	tokenFinded.setCreatedAt(new Date());
	    	tokenFinded.setExpirationAt(new Date());
	    	tokenFinded.setTokenString(tokenGenerated);
	    	tokenRepository.save(tokenFinded);
	    }
	    return tokenGenerated;
	}

	@Override
	public void deleteToken(User user) {
		TempToken tokenFinded = tokenRepository.findByUser(user);
		if (tokenFinded!=null)
			tokenRepository.delete(tokenFinded.getId());
	}

}
