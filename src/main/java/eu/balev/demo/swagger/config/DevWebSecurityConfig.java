package eu.balev.demo.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Enables in-memory authentication, CORS policy etc. Used by Swagger.
 * 
 * @author LBalev
 */
@Profile("dev")
@Configuration
public class DevWebSecurityConfig  extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(AuthenticationManagerBuilder authManagerBuilder)
			throws Exception {
		authManagerBuilder.
				inMemoryAuthentication().
				withUser("lucho").
				password("test").roles("USER");
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//@formatter:off
		
		// this is just for the H2 DB and testing purposes
		http.
			authorizeRequests().
			antMatchers("/h2-console/**").
			permitAll().
			and().
				authorizeRequests().
				antMatchers("/v2/api-docs").
				permitAll().
			and().	
				authorizeRequests().
				antMatchers("/swagger*/**").
				permitAll().
			and().
				authorizeRequests().
				anyRequest().
				authenticated().
			and().
				httpBasic();
				

		http.csrf().disable();
		http.headers().frameOptions().disable();
		//@formatter:on
	}
}
