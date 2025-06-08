package com.ProyectoFinal.api_rest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProyectoFinal.api_rest.entities.user;
import com.ProyectoFinal.api_rest.services.userService;
import com.ProyectoFinal.api_rest.utils.validationMessage;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("api/usuarios")
public class userController {

    @Autowired
    private userService usuarios;

    @GetMapping
    public List<user> list(){
        return usuarios.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable Long id) {
        Optional<user> optionalUser = usuarios.findById(id);
        if (optionalUser.isPresent())
            return ResponseEntity.ok(optionalUser.orElseThrow());
        return ResponseEntity.notFound().build();
    }

    //SE TIENE QUE MOVER
   @PostMapping
   public ResponseEntity<?> create(@Valid @RequestBody user usuario, BindingResult result) {   
        if (result.hasFieldErrors())
           return validationMessage.validation(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarios.save(usuario));
    } 

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody user usuario, BindingResult result) {
        if (result.hasFieldErrors())
            return validationMessage.validation(result);
        Optional<user> optionalUser = usuarios.update(id, usuario);
        if (optionalUser.isPresent())
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalUser.get());
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<user> userOptional = usuarios.delete(id);
        if(userOptional.isPresent())
            return ResponseEntity.ok(userOptional.get());
        return ResponseEntity.notFound().build();
    }
    
}
