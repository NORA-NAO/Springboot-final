package com.ProyectoFinal.api_rest.security.filter;

import com.ProyectoFinal.api_rest.entities.login;
import com.ProyectoFinal.api_rest.entities.user;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ProyectoFinal.api_rest.security.tokenJWTConfig.*;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final SecretKey secretKey;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, SecretKey secretKey) {
        this.authenticationManager = authenticationManager;
        this.secretKey = secretKey;
        setFilterProcessesUrl("/api/auth/login"); // Establece la URL de login
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, 
                                              HttpServletResponse response) 
                                              throws AuthenticationException {
        try {
            login user = new ObjectMapper().readValue(request.getInputStream(), login.class);
            String email = user.getUsername(); // Asumo que tienes getEmail() en tu entidad User
            String password = user.getPassword();

            UsernamePasswordAuthenticationToken authToken = 
                new UsernamePasswordAuthenticationToken(email, password);
            
            return authenticationManager.authenticate(authToken);
            
        } catch (IOException e) {
            throw new RuntimeException("Error al leer las credenciales", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                          HttpServletResponse response,
                                          FilterChain chain,
                                          Authentication authResult) 
                                          throws IOException {
        UserDetails userDetails = (UserDetails) authResult.getPrincipal();
        String username = userDetails.getUsername();
        
        // Convertir roles a formato string
        String authorities = userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(","));

        // Construir el token JWT
        String token = Jwts.builder()
            .subject(username)
            .claim("authorities", authorities)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + 1800000))
            .signWith(secretKey)
            .compact();

        // Añadir token al header
        response.addHeader(HEADER_AUTHORIZATION, PREFIX_TOKEN + token);

        // Crear cuerpo de la respuesta
        Map<String, Object> body = new HashMap<>();
        body.put("token", token);
        body.put("username", username);
        body.put("message", "Autenticación exitosa");
        body.put("authorities", authorities);

        response.setContentType(CONTEN_TYPE);
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.getWriter().flush();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            AuthenticationException failed) 
                                            throws IOException {
        Map<String, String> body = new HashMap<>();
        body.put("message", "Error en la autenticación: credenciales incorrectas");
        body.put("error", failed.getLocalizedMessage());

        response.setContentType(CONTEN_TYPE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.getWriter().flush();
    }
}
