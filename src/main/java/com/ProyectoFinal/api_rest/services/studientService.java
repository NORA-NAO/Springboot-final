package com.ProyectoFinal.api_rest.services;

import java.util.List;
import java.util.Optional;

import com.ProyectoFinal.api_rest.entities.nota;
import com.ProyectoFinal.api_rest.entities.studient;

public interface studientService {
     studient save( studient estudiante);
     List<studient> findAll();
     Optional<studient> findById(Long id);
     List<nota> findAllNotas(Long id);
}
