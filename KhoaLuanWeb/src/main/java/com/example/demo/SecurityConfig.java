package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.example.demo.security.CustomAuthenticationProvider;
import com.example.demo.security.CustomUserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		CustomAuthenticationProvider provider = new CustomAuthenticationProvider();	
		provider.setUserDetailService(customUserDetailsService);
		auth.authenticationProvider(provider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/","/trang-chu","/dang-nhap","/dang-ky","/doi-mat-khau").permitAll()
		.antMatchers("/dat-lich/**").authenticated()
		.and()
		.formLogin()
		.loginPage("/dang-nhap")
		.loginProcessingUrl("/doLogin")
		.usernameParameter("username")
		.passwordParameter("password")
		.defaultSuccessUrl("/")
		.permitAll();
		 http.logout()
		 .logoutSuccessUrl("/login")
		 .logoutUrl("/logout")
		 .permitAll();
		
	}

}
