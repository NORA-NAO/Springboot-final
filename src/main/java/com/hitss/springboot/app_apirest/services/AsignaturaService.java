package com.hitss.springboot.app_apirest.services;

import java.util.List;
import java.util.Optional;

import com.hitss.springboot.app_apirest.entities.Asignaturas;

public interface AsignaturaService {
    Asignaturas save(Asignaturas asig);
    List<Asignaturas> findAll();
    Optional<Asignaturas> findById(Long id);
    Optional<Asignaturas> update(Long id, Asignaturas as);
    Optional<Asignaturas> delete(Long id);

}
