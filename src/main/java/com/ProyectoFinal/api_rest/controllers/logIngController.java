package com.ProyectoFinal.api_rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProyectoFinal.api_rest.entities.user;
import com.ProyectoFinal.api_rest.repositories.userRepository;
import com.ProyectoFinal.api_rest.security.filter.JwtAuthenticationFilter2.LoginRequest;
import com.ProyectoFinal.api_rest.security.filter.JwtTokenUtil;
import com.ProyectoFinal.api_rest.services.userService;
import com.ProyectoFinal.api_rest.utils.validationMessage;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/auth")
public class logIngController {

    public record AuthResponse(String status, String message, String token, String username) {}
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private userService usuarios;

    private  JwtTokenUtil jwtTokenUtil;



    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.email(),
                    request.password()
                )
            );
            
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtTokenUtil.generateToken(userDetails);
            
            return ResponseEntity.ok(new AuthResponse(
                "success",
                "Autenticación exitosa",
                token,
                userDetails.getUsername()
            ));
            
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body(new AuthResponse(
                "error",
                "Credenciales inválidas",
                null,
                null
            ));
        }
    }


    @PostMapping("/registrer")
    public ResponseEntity<?> create(@Valid @RequestBody user usuario, BindingResult result) {
        if (result.hasFieldErrors())
            return validationMessage.validation(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarios.save(usuario));
    }

}
