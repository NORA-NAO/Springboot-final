package com.ProyectoFinal.api_rest.services;

import java.util.List;
import java.util.Optional;

import com.ProyectoFinal.api_rest.entities.asignaturas;
import com.ProyectoFinal.api_rest.entities.professor;
import com.ProyectoFinal.api_rest.entities.user;

public interface professorService {
    
    List<professor> findAll();
    Optional<professor> findById(Long id);
    professor save(professor profesor);
    List<asignaturas> allAsignaturas(Long id);
 
}
