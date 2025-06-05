package com.ProyectoFinal.api_rest.services;

import java.util.List;
import java.util.Optional;

import com.ProyectoFinal.api_rest.entities.nota;

public interface notaService {
    nota save(nota Note);
    List<nota> findForAssignature(Long id);
    List<nota> findForStudent(Long id);
    Optional<nota> update(Long id, nota note);
    Optional<nota> delete(Long id);



}
