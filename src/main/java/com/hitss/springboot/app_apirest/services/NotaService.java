package com.hitss.springboot.app_apirest.services;

import java.util.List;
import java.util.Optional;

import com.hitss.springboot.app_apirest.entities.Nota;

public interface NotaService {
    Nota save(Nota note);
    List<Nota> findByAssignature(Long id);
    List<Nota> findByStudent(Long id);
    Optional<Nota> update(Long id, Nota note);
    Optional<Nota> delete(Long id);
    
}
