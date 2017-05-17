package eu.balev.demo.swagger.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Applicable for non development environment.
 * 
 * @author LBalev
 */
@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//@formatter:off
			http.
				authorizeRequests().
				anyRequest().
				authenticated().//EVERYTHING IS PROTECTED
			and().
				httpBasic();//BASIC AUTH
			
			http.csrf().disable();	
		//@formatter:on
	}
}