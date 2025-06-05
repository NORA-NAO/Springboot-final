package com.ProyectoFinal.api_rest.services;

import java.util.List;
import java.util.Optional;

import com.ProyectoFinal.api_rest.entities.asignaturas;

public interface asignaturaService {
    asignaturas save(asignaturas asig);
    List<asignaturas> findAll();
    Optional<asignaturas> findById(Long id);
    Optional<asignaturas> update(Long id, asignaturas as);
    Optional<asignaturas> delete(Long id);
}
