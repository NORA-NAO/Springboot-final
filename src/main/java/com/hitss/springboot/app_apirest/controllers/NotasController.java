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
import com.hitss.springboot.app_apirest.entities.Nota;
import com.hitss.springboot.app_apirest.entities.Student;
import com.hitss.springboot.app_apirest.services.AsignaturaService;
import com.hitss.springboot.app_apirest.services.NotaService;
import com.hitss.springboot.app_apirest.services.StudentService;
import com.hitss.springboot.app_apirest.utils.validationMessage;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/notas")
public class NotasController {
    
    @Autowired
    private NotaService notas;

    @Autowired
    private AsignaturaService asignaturas;

    @Autowired
    private StudentService estudiantes;

    @PostMapping
    public ResponseEntity<?> registrarNota(@Valid @RequestBody Nota nota, BindingResult result) {
        if (result.hasFieldErrors())
            return validationMessage.validation(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(notas.save(nota));
    }

    @GetMapping("/asignatura/{id}")
    public List<Nota> notasAsignatura(@PathVariable Long id) {
        Asignaturas asig = asignaturas.findById(id).orElseThrow();
        return notas.findByAsignatura(asig);
    }
    
    @GetMapping("/estudiante/{id}")
    public List<Nota> notasEstudiante(@PathVariable Long id) {
        Student estudiante = estudiantes.findById(id).orElseThrow();
        return notas.findByEstudiante(estudiante);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> editarNota(@PathVariable Long id, @Valid @RequestBody Nota nota, BindingResult result) {
        if (result.hasFieldErrors())
            return validationMessage.validation(result);
        Optional<Nota> optionalNota = notas.update(id, nota);
        if (optionalNota.isPresent())
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalNota.get());
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Nota> notaOptional = notas.delete(id);
        if(notaOptional.isPresent()){
            Nota nota = notaOptional.orElseThrow();
            return ResponseEntity.ok(nota);
        }
        return ResponseEntity.notFound().build();
    }
}
