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
import com.hitss.springboot.app_apirest.entities.Professor;
import com.hitss.springboot.app_apirest.services.ProfessorService;
import com.hitss.springboot.app_apirest.utils.validationMessage;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/profesores")
public class ProfessorController {

    @Autowired
    private ProfessorService profesores;

    @PostMapping
    public ResponseEntity<?> crearProfesor(@Valid @RequestBody Professor profesor, BindingResult result) {
        if (result.hasFieldErrors())
            return validationMessage.validation(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(profesores.save(profesor));
    }
    
    @GetMapping
    public List<Professor> listadoProfesores() {
        return profesores.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> detalles(@PathVariable Long id) {
        Optional<Professor> profe = profesores.findById(id);
        if (profe.isPresent())
            return ResponseEntity.ok(profe.orElseThrow());
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/asignaturas")
    public List<Asignaturas> getMethodName(@PathVariable Long id) {
        return profesores.allAsignaturas(id);
    }
}
