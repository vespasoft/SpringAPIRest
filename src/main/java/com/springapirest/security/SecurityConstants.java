package com.springapirest.security;

/**
 * Created by Luigi Vespa on 27/06/17.
 */
public class SecurityConstants {
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String HEADER_USER_STRING = "GET";
    public static final String SIGN_UP_URL = "/api/users";
    public static final String LOG_IN_URL = "/api/authenticator";
}
