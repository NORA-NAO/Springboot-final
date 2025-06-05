package com.ProyectoFinal.api_rest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProyectoFinal.api_rest.entities.nota;
import com.ProyectoFinal.api_rest.entities.studient;
import com.ProyectoFinal.api_rest.services.studientService;
import com.ProyectoFinal.api_rest.utils.validationMessage;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/estudiantes")
public class studentController {
    
    @Autowired
    private studientService estudiantes;

    @PostMapping
    public ResponseEntity<?> crearEstudiante(@Valid @RequestBody studient estudiante, BindingResult result) {
        if (result.hasFieldErrors())
            return validationMessage.validation(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(estudiantes.save(estudiante));
    }
    
    @GetMapping
    public List<studient> listado() {
        return estudiantes.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalles(@PathVariable Long id) {
        Optional<studient> estudiante = estudiantes.findById(id);
        if (estudiante.isPresent())
            return ResponseEntity.ok(estudiante.orElseThrow());
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/{id}/notas")
    public List<nota> getMethodName(@PathVariable Long id) {
        return estudiantes.findAllNotas(id);
    }
    
}
