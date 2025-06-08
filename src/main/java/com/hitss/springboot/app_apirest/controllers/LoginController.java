package com.hitss.springboot.app_apirest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hitss.springboot.app_apirest.entities.User;
import com.hitss.springboot.app_apirest.services.UserService;
import com.hitss.springboot.app_apirest.utils.validationMessage;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class LoginController {
    
    @Autowired
    private UserService usuarios;
    
    @PostMapping("/register")
    public ResponseEntity<?> create(@Valid @RequestBody User usuario, BindingResult result) {
        if (result.hasFieldErrors())
            return validationMessage.validation(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarios.save(usuario));
    }
}
