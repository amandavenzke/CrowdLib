package com.crowdlib.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Order(1)
@Configuration
@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private ClientDetailsService clientDetailsService;
	
	@Bean
	public BasicAuthenticationFilter basicAuthenticationFilter() {
		
	    return new BasicAuthenticationFilter((authentication) -> {
	    	ClientDetails clientDetails = null;
    	
	        try {
	        	clientDetails = this.clientDetailsService.loadClientByClientId(authentication.getPrincipal().toString());
	        	if (!BCrypt.checkpw((String)authentication.getCredentials(), clientDetails.getClientSecret())){
	            	throw new BadCredentialsException("Secret inválido.");
	            }
	            
	        } catch (ClientRegistrationException e) {
	            throw new BadCredentialsException("Client ID inválido");
	        }
	        return new OAuth2Authentication(
	                new OAuth2Request(null, clientDetails.getClientId(), clientDetails.getAuthorities(), true,
	                        clientDetails.getScope(), clientDetails.getResourceIds(), null, null, null),
	                null);
	    });
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/usuarios")
			.addFilterBefore(this.basicAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests().anyRequest().authenticated()
			.and()
			.httpBasic();
	}

}
