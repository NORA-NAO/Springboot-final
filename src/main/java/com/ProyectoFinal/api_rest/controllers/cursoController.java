package com.ProyectoFinal.api_rest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProyectoFinal.api_rest.entities.curso;
import com.ProyectoFinal.api_rest.services.cursoService;
import com.ProyectoFinal.api_rest.utils.validationMessage;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/cursos")
public class cursoController {
    
    @Autowired
    private cursoService cursos;

    @PostMapping
    public ResponseEntity<?> crearCurso(@Valid @RequestBody curso c, BindingResult result) {
        if (result.hasFieldErrors())
            return validationMessage.validation(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursos.save(c));
    }
    
    @GetMapping
    public List<curso> listarCursos() {
        return cursos.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalles(@PathVariable Long id) {
        Optional<curso> c = cursos.findById(id);
        if(c.isPresent())
            return ResponseEntity.ok(c.orElseThrow());
        return ResponseEntity.notFound().build();
    }
    
    
}
