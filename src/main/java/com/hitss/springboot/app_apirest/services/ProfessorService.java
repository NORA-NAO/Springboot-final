package com.hitss.springboot.app_apirest.services;

import com.hitss.springboot.app_apirest.entities.Professor;

import java.util.Optional;
import java.util.List;

import com.hitss.springboot.app_apirest.entities.Asignaturas;

public interface ProfessorService {
    List<Professor> findAll();
    Optional<Professor> findById(Long id);
    Professor save(Professor profesor);
    List<Asignaturas> allAsignaturas(Long id);
    
}
