package com.ProyectoFinal.api_rest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProyectoFinal.api_rest.entities.asignaturas;
import com.ProyectoFinal.api_rest.entities.professor;
import com.ProyectoFinal.api_rest.services.professorService;
import com.ProyectoFinal.api_rest.utils.validationMessage;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/profesores")
public class professorController {

    @Autowired
    private professorService profesores;

    @PostMapping
    public ResponseEntity<?> crearProfesor(@Valid @RequestBody professor profesor, BindingResult result) {
        if (result.hasFieldErrors())
            return validationMessage.validation(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(profesores.save(profesor));
    }
    
    @GetMapping
    public List<professor> listadoProfesores() {
        return profesores.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> detalles(@PathVariable Long id) {
        Optional<professor> profe = profesores.findById(id);
        if (profe.isPresent())
            return ResponseEntity.ok(profe.orElseThrow());
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/asignaturas")
    public List<asignaturas> getMethodName(@PathVariable Long id) {
        return profesores.allAsignaturas(id);
    }
    
}
