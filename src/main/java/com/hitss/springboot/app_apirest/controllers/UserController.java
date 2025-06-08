package com.hitss.springboot.app_apirest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hitss.springboot.app_apirest.entities.User;
import com.hitss.springboot.app_apirest.services.UserService;
import com.hitss.springboot.app_apirest.utils.validationMessage;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {

	@Autowired
    private UserService usuarios;

    @GetMapping
    public List<User> list(){
        return usuarios.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable Long id) {
        Optional<User> optionalUser = usuarios.findById(id);
        if (optionalUser.isPresent())
            return ResponseEntity.ok(optionalUser.orElseThrow());
        return ResponseEntity.notFound().build();
    }

    //SE TIENE QUE MOVER
   

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody User usuario, BindingResult result) {
        if (result.hasFieldErrors())
            return validationMessage.validation(result);
        Optional<User> optionalUser = usuarios.update(id, usuario);
        if (optionalUser.isPresent())
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalUser.get());
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<User> userOptional = usuarios.delete(id);
        if(userOptional.isPresent())
            return ResponseEntity.ok(userOptional.get());
        return ResponseEntity.notFound().build();
    }
}
