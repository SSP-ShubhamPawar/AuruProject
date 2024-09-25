package com.auru.config;

//@EnableWebSecurity
public class SpringSecurityConfig{/*
extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource datasource;

	// For authentication Authentication manger need to override
	  
	  @Override protected void configure(AuthenticationManagerBuilder auth) throws
	  Exception { PasswordEncoder encoder =
	  PasswordEncoderFactories.createDelegatingPasswordEncoder();
	  auth.inMemoryAuthentication().withUser("shubham").password(encoder.encode(
	  "shubham")).roles("TEAM LEAD") .and()
	  .withUser("snehal").password(encoder.encode("snehal")).roles("HR");
	  
	  }
	  
	  
	  
	  @Bean 
	  PasswordEncoder getPasswordEncoder() 
	  { return NoOpPasswordEncoder
	  authenticationManagerBean(); 
	  }
	  
	  
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				// .anyRequest().permitAll().and().formLogin(); This method gives access t all
				// the POST request
				.antMatchers("/api/employees").hasRole("ADMIN")
				.antMatchers("/").permitAll().and()
				.formLogin();
	}
	 @Override public void configure(WebSecurity web) throws Exception {
		 
		  web.ignoring().antMatchers("/h2-console/**"); 
		  }

*/
	}
