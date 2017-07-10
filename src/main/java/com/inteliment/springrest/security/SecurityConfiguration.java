package com.inteliment.springrest.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.inteliment.springrest.utility.CounterPropertyReader;

/**
 * Adding Spring Security in Application
 * <P>
 * Require authentication to every URL in your application
 * Generate a login form
 * Allow the user with the Username user and the Password password to authenticate with form based authentication
 * @author Dhaval Thakkar
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	public static String CLASSNAME = "SecurityConfiguration";
    private final Logger LOG = LoggerFactory.getLogger(SecurityConfiguration.class);
	
	private static String REALM="WORD_COUNT_REALM";

	/**
	 * Method is used to configure Global Security
	 * @param auth
	 * @throws Exception
	 */
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		LOG.info(CLASSNAME + " configureGlobalSecurity Calling...");
		auth.inMemoryAuthentication().withUser(CounterPropertyReader.getCounterProperty().getUserName()).password(CounterPropertyReader.getCounterProperty().getPassword()).roles("ADMIN");
	}

	/**
	 * Method is used to configure security in http request for matching URI only
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		LOG.info(CLASSNAME + " configure(http) Calling...");
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/counter-api/**").hasRole("ADMIN")
		.and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//We don't need sessions to be created.
	}

	/**
	 * Method will return Custom Authentication Entry Point
	 * 
	 * @return CustomBasicAuthenticationEntryPoint
	 */
	@Bean
	public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
		LOG.info(CLASSNAME + " getBasicAuthEntryPoint() Calling...");
		return new CustomBasicAuthenticationEntryPoint();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		LOG.info("configure(WebSecurity web) Calling...");
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}
}