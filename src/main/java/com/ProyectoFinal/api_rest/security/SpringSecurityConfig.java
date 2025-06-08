package com.ProyectoFinal.api_rest.security;

import static com.ProyectoFinal.api_rest.security.tokenJWTConfig.SECRET_KEY;

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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ProyectoFinal.api_rest.security.filter.JwtAuthenticationFilter;
import com.ProyectoFinal.api_rest.security.filter.JwtAuthenticationFilter2;

@Configuration
public class SpringSecurityConfig {
    private String prof = "PROFESSOR";
    private String admin = "ADMIN";
    private String estdudiante = "STUDENT";
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;
    private JwtAuthenticationFilter2 jwtAuthFilter;

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    

    // indicar todas las rutas permitidas y no permitidas para los roles
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, "/api/estudiantes")
                .hasRole("ADMIN")
                .requestMatchers("api/auth/login").permitAll()
                .requestMatchers(HttpMethod.POST,"/api/usuarios").permitAll()
                /*
                 * .requestMatchers("api/usuarios", "/api/asignaturas", "/api/cursos",
                 * "/api/periodos")
                 * .hasRole(admin)
                 * .requestMatchers(HttpMethod.GET, "/api/profesores", "/api/profesores/{id}",
                 * "/api/estudiantes", "/api/estudiantes/{id}")
                 * .hasRole(admin)
                 * .requestMatchers(HttpMethod.GET, "/api/profesores/{id}/asignaturas",
                 * "/api/asignaturas",
                 * "/api/asignaturas/{id}")
                 * .hasRole(prof)
                 * .requestMatchers(HttpMethod.GET,
                 * "/api/estudiantes/{id}/notas").hasRole(estdudiante)
                 */
                .anyRequest().authenticated())
                .formLogin(login -> login.loginProcessingUrl("/api/auth/login")
                .permitAll())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class) //new JwtAuthenticationFilter(authenticationManager(), SECRET_KEY)
                .csrf(config -> config.disable())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
}
