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
						.requestMatchers("/error").permitAll()
						.requestMatchers("/api/auth").hasRole("ADMIN")
						.requestMatchers("/api/usuarios").hasRole("ADMIN")
						.requestMatchers("/api/profesores/").hasRole("ADMIN")
						.requestMatchers("/api/profesores/{id}").hasRole("ADMIN")
						.requestMatchers("/api/profesores/{id}/asignaturas").hasRole("PROFESSOR")
						.requestMatchers("/api/estudiantes/").hasRole("ADMIN")
						.requestMatchers("/api/estudiantes/{id}").hasRole("ADMIN")
						.requestMatchers("/api/estudiantes/{id}/notas").hasRole("STUDENT")
						.requestMatchers("/api/asignaturas").hasRole("ADMIN")
						.requestMatchers("/api/asignaturas/{id}").hasRole("ADMIN")
						.requestMatchers(HttpMethod.GET, "/api/asignaturas/").hasRole("PROFESSOR")
						.requestMatchers(HttpMethod.GET, "/api/asignaturas/{id}").hasRole("PROFESSOR")
						.requestMatchers("/api/cursos").hasRole("ADMIN")
						.requestMatchers("/api/periodos").hasRole("ADMIN")
						.requestMatchers(HttpMethod.POST, "/api/notas/").hasRole("PROFESSOR")
						.requestMatchers(HttpMethod.GET, "/api/notas/asignatura/{id}").hasRole("PROFESSOR")
						.requestMatchers(HttpMethod.GET, "/api/notas/{id}").hasRole("STUDENT")
						.requestMatchers(HttpMethod.PUT, "/api/notas/{id}").hasRole("PROFESSOR")
						.requestMatchers(HttpMethod.DELETE, "/api/notas/{id}").hasRole("PROFESSOR")
						.requestMatchers(HttpMethod.POST, "/api/materiales/").hasRole("PROFESSOR")
						.requestMatchers(HttpMethod.GET, "/api/materiales/asignatura/{id}").hasRole("STUDENT")
						.requestMatchers(HttpMethod.GET, "/api/materiales/{id}").hasRole("PROFESSOR")
						.requestMatchers(HttpMethod.DELETE, "/api/materiales/{id}").hasRole("PROFESSOR")
						.requestMatchers("/api/reportes").hasRole("ADMIN")
						.anyRequest().authenticated())
						.addFilter(new JwtAuthenticationFilter(authenticationManager()))
						.addFilter(new JwtValidationFilter(authenticationManager()))
					.csrf(config -> config.disable())
					.sessionManagement(managemet -> managemet
							.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
					.build();
	}

}
