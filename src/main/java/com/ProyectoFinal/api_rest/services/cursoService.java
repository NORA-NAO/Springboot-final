package com.ProyectoFinal.api_rest.services;

import java.util.List;
import java.util.Optional;

import com.ProyectoFinal.api_rest.entities.curso;

public interface cursoService {
    curso save(curso c);
    List<curso> findAll();
    Optional<curso> findById(Long id);
}
