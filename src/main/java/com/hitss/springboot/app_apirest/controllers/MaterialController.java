package com.hitss.springboot.app_apirest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hitss.springboot.app_apirest.entities.Material;
import com.hitss.springboot.app_apirest.services.MaterialService;
import com.hitss.springboot.app_apirest.utils.validationMessage;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/materiales")
public class MaterialController {
    
    @Autowired
    private MaterialService materiales;

    @PostMapping
    public ResponseEntity<?> subirMaterial(@Valid @RequestBody Material material, BindingResult result) {
        if (result.hasFieldErrors())
            return validationMessage.validation(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(materiales.save(material));
    }

    @GetMapping("/asignatura/{id}")
    public List<Material> materialesAsignatura(@PathVariable Long id) {
        return materiales.findByAssignature(id);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarMaterial(@PathVariable Long id){
        Optional<Material> materialOptional = materiales.delete(id);
        if(materialOptional.isPresent()){
            Material material = materialOptional.orElseThrow();
            return ResponseEntity.ok(material);
        }
        return ResponseEntity.notFound().build();
    }
}
