package com.example.demo.security;

import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomAuthenticationProvider implements AuthenticationProvider{
	CustomUserDetailsService customUserDetailsService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		try {
		CustomUserDetails user = (CustomUserDetails) customUserDetailsService.loadUserByUsername(authentication.getName());
		UsernamePasswordAuthenticationToken result = null;
        if (user.getUsername().equals(authentication.getName()) && user.getPassword().equals(authentication.getCredentials().toString())) {
            result = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<GrantedAuthority>());
        }
        return result;
    } catch (UsernameNotFoundException e) {
        throw e;
    }
	}
	
	public void setUserDetailService(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }
	
	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
