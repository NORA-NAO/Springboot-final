package com.hitss.springboot.app_apirest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hitss.springboot.app_apirest.entities.Nota;
import com.hitss.springboot.app_apirest.entities.Student;
import com.hitss.springboot.app_apirest.services.StudentService;
import com.hitss.springboot.app_apirest.utils.validationMessage;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/estudiantes")
public class StudentController {
    
    @Autowired
    private StudentService estudiantes;

    @PostMapping
    public ResponseEntity<?> crearEstudiante(@Valid @RequestBody Student estudiante, BindingResult result) {
        if (result.hasFieldErrors())
            return validationMessage.validation(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(estudiantes.save(estudiante));
    }
    
    @GetMapping
    public List<Student> listado() {
        return estudiantes.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalles(@PathVariable Long id) {
        Optional<Student> estudiante = estudiantes.findById(id);
        if (estudiante.isPresent())
            return ResponseEntity.ok(estudiante.orElseThrow());
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/{id}/notas")
    public List<Nota> getMethodName(@PathVariable Long id) {
        return estudiantes.findAllNotas(id);
    }
}
