package com.hitss.springboot.app_apirest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.hitss.springboot.app_apirest.security.filter.JwtAuthenticationFilter;
import com.hitss.springboot.app_apirest.security.filter.JwtValidationFilter;

@Configuration
public class SpringSecurityConfig {
	
	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	
	@Bean
	AuthenticationManager authenticationManager() throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests(auth -> auth
						.requestMatchers(HttpMethod.GET, "/api/estudiantes")
						.hasRole("ADMIN")
						.requestMatchers("/api/auth/register").permitAll()
						.requestMatchers("/error").permitAll()
						.requestMatchers(HttpMethod.POST,"/api/usuarios").permitAll()
						.requestMatchers("/api/usuarios", "/api/asignaturas", "/api/cursos",
						"/api/periodos")
						.hasRole("ADMIN")
						.requestMatchers(HttpMethod.GET, "/api/profesores", "/api/profesores/{id}",
						"/api/estudiantes", "/api/estudiantes/{id}")
						.hasRole("ADMIN")
						.requestMatchers(HttpMethod.GET, "/api/profesores/{id}/asignaturas",
						"/api/asignaturas",
						"/api/asignaturas/{id}")
						.hasRole("PROFESSOR")
						.requestMatchers(HttpMethod.GET,
						"/api/estudiantes/{id}/notas").hasRole("STUDENT")
						.anyRequest().authenticated())
						.addFilter(new JwtAuthenticationFilter(authenticationManager()))
						.addFilter(new JwtValidationFilter(authenticationManager()))
					.csrf(config -> config.disable())
					.sessionManagement(managemet -> managemet
							.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
					.build();
	}

}
