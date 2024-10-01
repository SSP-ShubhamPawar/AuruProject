package com.auru.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	//private PasswordEncoder NoOpPasswordEncoder;
	// @Autowired
	// DataSource datasource;

	// For authentication Authentication manger need to override

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		auth.inMemoryAuthentication()
				.withUser("shubham").password(encoder.encode("shubham")).roles("TEAM LEAD").and()
				.withUser("snehal").password(encoder.encode("snehal")).roles("HR");

	}

	/*
	 * @Bean PasswordEncoder getPasswordEncoder() { return NoOpPasswordEncoder
	 * authenticationManagerBean(); }
	 */

	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				 .anyRequest().permitAll().and().formLogin(); //This method gives access t all the POST request
				//.antMatchers("/addEvent").hasRole("TEAM LEAD").antMatchers("/").permitAll().and().formLogin();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {

		//web.ignoring().antMatchers("/h2-console/**");
	}

}
