package com.myapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class MySecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(MySecurityConfiguration.class);
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		LOGGER.info("*** MySecurityConfiguration *** configure ****");

		http.authorizeRequests()
				.antMatchers("/", "/index", "/login", "/createAccount", "/templates/**", "/resources/**", "/js/**",
						"/lib/**", "/css/**")
				.permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login").defaultSuccessUrl("/")
				.and().addFilterAfter(new MyCsrfHeaderFilter(), CsrfFilter.class).logout().logoutUrl("/logout");

		// CSRF is disabled. You can enable by introducing CSRF filter.
		//http.csrf().disable();
		http.csrf().csrfTokenRepository(csrfTokenRepository());
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		LOGGER.info("*** MySecurityConfiguration *** configureGlobal ****");

		/*
		 * 1. The below is for in-memory authentication for predefined user
		 * name/password
		 * 
		 * auth.inMemoryAuthentication().withUser("admin").password("admin").roles(
		 * "USER");
		 */

		// 2. Authentication based on Database
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
	
	/**
	 * The other thing we have to do on the server is tell Spring 
	 * Security to expect the CSRF token in the format that 
	 * Angular wants to send it back (a header called �X-XRSF-TOKEN� instead of the 
	 * default �X-CSRF-TOKEN�). We do this by customizing the CSRF filter:
	 * @return
	 */
	private CsrfTokenRepository csrfTokenRepository() {
		  HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		  repository.setHeaderName("X-XSRF-TOKEN");
		  return repository;
		}
}