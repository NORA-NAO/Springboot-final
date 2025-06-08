package com.hitss.springboot.app_apirest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hitss.springboot.app_apirest.entities.Curso;
import com.hitss.springboot.app_apirest.services.CursoService;
import com.hitss.springboot.app_apirest.utils.validationMessage;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursos;

    @PostMapping
    public ResponseEntity<?> crearCurso(@Valid @RequestBody Curso c, BindingResult result) {
        if (result.hasFieldErrors())
            return validationMessage.validation(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursos.save(c));
    }
    
    @GetMapping
    public List<Curso> listarCursos() {
        return cursos.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalles(@PathVariable Long id) {
        Optional<Curso> c = cursos.findById(id);
        if(c.isPresent())
            return ResponseEntity.ok(c.orElseThrow());
        return ResponseEntity.notFound().build();
    }
}
