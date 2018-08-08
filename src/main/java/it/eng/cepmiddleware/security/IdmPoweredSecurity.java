package it.eng.cepmiddleware.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class IdmPoweredSecurity extends WebSecurityConfigurerAdapter {

	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.addFilterAt(new IdmAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/exposed-engine/**").hasAnyRole("ADMIN", "USER")
			.anyRequest().hasRole("ADMIN")
		.and()
			.sessionManagement()
	        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;
    }

}
