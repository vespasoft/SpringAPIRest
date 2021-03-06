package com.springapirest.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.context.annotation.Bean;

import static com.springapirest.security.SecurityConstants.URL_DOCUMENTATION;
import static com.springapirest.security.SecurityConstants.URL_SIGN_UP;
import static com.springapirest.security.SecurityConstants.URL_LOG_IN;
import static com.springapirest.security.SecurityConstants.URL_COUNTRIES;
import static com.springapirest.security.SecurityConstants.URL_CITIES;
import static com.springapirest.security.SecurityConstants.URL_CATEGORIES;
import static com.springapirest.security.SecurityConstants.URL_PRODUCTS;
import static com.springapirest.security.SecurityConstants.URL_USERS;

import java.util.Arrays;

/**
 * Created by Luigi Vespa on 27/06/17.
 */
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	private UserDetailsService userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurity(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
        	/*.antMatchers(HttpMethod.GET, "/").permitAll()
        	.antMatchers(HttpMethod.GET, URL_DOCUMENTATION).permitAll()
        	.antMatchers(HttpMethod.GET, URL_COUNTRIES).permitAll()
        	.antMatchers(HttpMethod.GET, URL_COUNTRIES + "/{id}").permitAll()
        	.antMatchers(HttpMethod.GET, URL_CITIES).permitAll()
        	.antMatchers(HttpMethod.POST, URL_SIGN_UP).permitAll()
            .antMatchers(HttpMethod.POST, URL_LOG_IN).permitAll()*/
        	.antMatchers(HttpMethod.GET, "/lang-logo.png").permitAll()
            .antMatchers(HttpMethod.GET, URL_PRODUCTS).authenticated()
            .antMatchers(HttpMethod.GET, URL_PRODUCTS +"/").authenticated()
            .antMatchers(HttpMethod.GET, URL_USERS).authenticated()
            .antMatchers(HttpMethod.GET, URL_USERS +"/").authenticated()
            .antMatchers(HttpMethod.GET, URL_CATEGORIES).authenticated()
            .antMatchers(HttpMethod.GET, URL_CATEGORIES +"/").authenticated()
            //.anyRequest().authenticated()
            .and()
            .addFilterBefore(new AuthenticationFilter(URL_LOG_IN, authenticationManager()), UsernamePasswordAuthenticationFilter.class)
            .addFilter(new AuthorizationFilter(authenticationManager()))
            // this disables session creation on Spring Security
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    final CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("*"));
    configuration.setAllowedMethods(Arrays.asList("HEAD",
            "GET", "POST", "PUT", "DELETE", "PATCH"));
    configuration.setAllowCredentials(true);
    configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}
