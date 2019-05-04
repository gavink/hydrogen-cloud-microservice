package com.hycloud.auth.sso.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Autowired
	DefaultTokenServices tokenService;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenServices(tokenService);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.antMatcher("/api/**")
			.authorizeRequests()
				.antMatchers("/api/users/**")
				.hasRole("ADMIN")
				.and()
			.authorizeRequests()
				.antMatchers("/api/clients/**")
				.hasRole("ADMIN")
				.and()
			.authorizeRequests()
				.antMatchers("/api/roles/**")
				.hasRole("ADMIN")
				.and()
			.authorizeRequests()
				.anyRequest()
				.authenticated();
	}

}
