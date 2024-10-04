package com.auru.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/*
 * Bcrypt is a type of password encoder and we are going to encrypt our password with this password encoder and store it in 
 * our application in memory for authentication
 * 
 * Bcrypt is also known as one way encoder ,encoded password cannot be decoded baxk to original password
 * */

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	// Annotation
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().passwordEncoder(passwordEncoder())
				.withUser("shubham").password(passwordEncoder().encode("pass")).roles("ADMIN").and().passwordEncoder(passwordEncoder())
				.withUser("pawar").password(passwordEncoder().encode("pass")).roles("BASIC");
	}

	@Bean
	// Method
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Annotation
	@Override
	// Method
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests()
				.antMatchers("/deleteEvent/{eventName}","/updateEvent").hasRole("ADMIN")
				.antMatchers("/searchEvent/","/addEvent").hasAnyRole("BASIC","ADMIN")
				.anyRequest().authenticated().and().formLogin().permitAll();
		}
}
