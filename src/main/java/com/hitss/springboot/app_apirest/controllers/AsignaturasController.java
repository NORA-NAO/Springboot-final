package com.hitss.springboot.app_apirest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hitss.springboot.app_apirest.entities.Asignaturas;
import com.hitss.springboot.app_apirest.services.AsignaturaService;
import com.hitss.springboot.app_apirest.utils.validationMessage;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/asignaturas")
public class AsignaturasController {
    
    @Autowired
    private AsignaturaService asigs;

    @PostMapping
    public ResponseEntity<?> crearAsignatura(@Valid @RequestBody Asignaturas asig, BindingResult result) {
        if (result.hasFieldErrors())
            return validationMessage.validation(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(asigs.save(asig));
    }
    
    @GetMapping
    public List<Asignaturas> listado() {
        return asigs.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Asignaturas> asig = asigs.findById(id);
        if (asig.isPresent())
            return ResponseEntity.ok(asig.orElseThrow());
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable Long id, @Valid @RequestBody Asignaturas asig, BindingResult result) {
        if (result.hasFieldErrors())
            return validationMessage.validation(result);
        Optional<Asignaturas> optionalAsig = asigs.update(id, asig);
        if (optionalAsig.isPresent())
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalAsig.get());
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<Asignaturas> optionalAsig = asigs.delete(id);
        if (optionalAsig.isPresent())
            return ResponseEntity.ok(optionalAsig.get());
        return ResponseEntity.notFound().build();
    }
}
