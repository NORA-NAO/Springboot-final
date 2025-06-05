package com.ProyectoFinal.api_rest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProyectoFinal.api_rest.entities.periodoLectivo;
import com.ProyectoFinal.api_rest.services.periodoLectivoService;
import com.ProyectoFinal.api_rest.utils.validationMessage;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/periodos")
public class periodoLectivoController {
    
    @Autowired
    private periodoLectivoService periodos;

    @PostMapping
    public ResponseEntity<?> crearPeriodo(@Valid @RequestBody periodoLectivo periodo, BindingResult result) {
        if (result.hasFieldErrors())
            return validationMessage.validation(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(periodos.save(periodo));
    }
    
    @GetMapping
    public List<periodoLectivo> listar() {
        return periodos.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalles(@PathVariable Long id) {
        Optional<periodoLectivo> periodo = periodos.findById(id);
        if (periodo.isPresent())
            return ResponseEntity.ok(periodo.orElseThrow());
        return ResponseEntity.notFound().build();
    }
    
}
