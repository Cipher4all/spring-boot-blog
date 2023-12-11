package com.webapp.blogDemo.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import org.thymeleaf.spring6.expression.Mvc;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception{
		http
			.csrf(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests(auth -> {
				auth.requestMatchers(mvc.pattern("/css/**")).permitAll();
				auth.requestMatchers(mvc.pattern("/js/**")).permitAll();
                auth.requestMatchers(mvc.pattern("/images/**")).permitAll();
                auth.requestMatchers(mvc.pattern("/fonts/**")).permitAll();
                auth.requestMatchers(mvc.pattern("/webjars/**")).permitAll();
                auth.requestMatchers(mvc.pattern("/")).permitAll();
                auth.requestMatchers(mvc.pattern("/rss/**")).permitAll();
                auth.requestMatchers(mvc.pattern("/register/**")).permitAll();
                auth.requestMatchers(mvc.pattern("/posts/**")).permitAll();
                auth.requestMatchers(PathRequest.toH2Console()).permitAll();
                auth.anyRequest().authenticated();
			})
			
			.formLogin(form -> form
					.loginPage("/login")
					.loginProcessingUrl("/login")
					.usernameParameter("email")
					.passwordParameter("password")
					.defaultSuccessUrl("/")
					.failureUrl("/login?error")
					.permitAll()
			);
		return http.build();
	}
	
	@Scope("prototype")
	@Bean
	MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector){
		return new MvcRequestMatcher.Builder(introspector);
	}
}

