package com.springapirest.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.springapirest.model.User;
import com.springapirest.repository.UserRepository;
import com.springapirest.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static com.springapirest.security.SecurityConstants.EXPIRATION_TIME;
import static com.springapirest.security.SecurityConstants.HEADER_STRING;
import static com.springapirest.security.SecurityConstants.HEADER_USER_STRING;
import static com.springapirest.security.SecurityConstants.SECRET;
import static com.springapirest.security.SecurityConstants.SIGN_UP_URL;
import static com.springapirest.security.SecurityConstants.TOKEN_PREFIX;
import static java.util.Collections.emptyList;

/**
 * Created by Luigi Vespa on 27/06/17.
 */
public class TokenAuthenticationManager {
	
	static void addAuthentication(HttpServletResponse res, String username) {
    	
        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
       
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
            String user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            return user != null ?
                    new UsernamePasswordAuthenticationToken(user, null, emptyList()) :
                    null;
        }
        return null;
    }
}
