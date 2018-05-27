package com.springapirest.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import static java.util.Collections.emptyList;

import static com.springapirest.security.SecurityConstants.EXPIRATION_TIME;
import static com.springapirest.security.SecurityConstants.HEADER_STRING;
import static com.springapirest.security.SecurityConstants.SECRET;
import static com.springapirest.security.SecurityConstants.TOKEN_PREFIX;


/**
 * Created by Luigi Vespa on 27/06/17.
 */
public class TokenAuthenticationManager {
	
	private TokenAuthenticationManager() {
		super();
	}

	static void addAuthentication(HttpServletResponse res, String username) {
    	
        String user_jwt = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + user_jwt);
       
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
