package com.ProyectoFinal.api_rest.services;

import java.util.List;
import java.util.Optional;

import com.ProyectoFinal.api_rest.entities.periodoLectivo;

public interface periodoLectivoService {
    periodoLectivo save(periodoLectivo periodo);
    List<periodoLectivo> findAll();
    Optional<periodoLectivo> findById(Long id);
}
