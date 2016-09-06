package com.spring.nikita.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	  http.authorizeRequests()
	  	.antMatchers("/").permitAll()
		.antMatchers("/main").permitAll()
		.antMatchers("/registration").permitAll()
		.antMatchers("/user/**").access("hasRole('USER')")
	  	.antMatchers("/admin/**").access("hasRole('ADMIN')")
		.antMatchers("/main/add").access("hasRole('ADMIN')")
		.antMatchers("/main/add").access("hasRole('USER')")
		.antMatchers("/my_page").access("hasRole('ADMIN')")
		.antMatchers("/my_page").access("hasRole('USER')")
		.antMatchers("/info/**").access("hasRole('ADMIN')")
		.antMatchers("/info/**").access("hasRole('USER')")
	  	.and().formLogin().loginPage("/login")
	  	.usernameParameter("login").passwordParameter("password")
	  	.and().csrf()
	  	.and().exceptionHandling().accessDeniedPage("/Access_Denied")
		.and().logout()
			  .permitAll()
			  .logoutUrl("/logout")
			  .logoutSuccessUrl("/")
			  .invalidateHttpSession(true);
	}
}
