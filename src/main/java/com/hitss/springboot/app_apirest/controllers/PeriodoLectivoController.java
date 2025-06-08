package com.hitss.springboot.app_apirest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hitss.springboot.app_apirest.entities.PeriodoLectivo;
import com.hitss.springboot.app_apirest.services.PeriodoLectivoService;
import com.hitss.springboot.app_apirest.utils.validationMessage;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/periodos")
public class PeriodoLectivoController {
    
    @Autowired
    private PeriodoLectivoService periodos;

    @PostMapping
    public ResponseEntity<?> crearPeriodo(@Valid @RequestBody PeriodoLectivo periodo, BindingResult result) {
        if (result.hasFieldErrors())
            return validationMessage.validation(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(periodos.save(periodo));
    }
    
    @GetMapping
    public List<PeriodoLectivo> listar() {
        return periodos.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalles(@PathVariable Long id) {
        Optional<PeriodoLectivo> periodo = periodos.findById(id);
        if (periodo.isPresent())
            return ResponseEntity.ok(periodo.orElseThrow());
        return ResponseEntity.notFound().build();
    }
}
