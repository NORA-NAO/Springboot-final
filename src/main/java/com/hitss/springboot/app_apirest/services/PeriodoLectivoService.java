package com.hitss.springboot.app_apirest.services;

import java.util.List;
import java.util.Optional;

import com.hitss.springboot.app_apirest.entities.PeriodoLectivo;

public interface PeriodoLectivoService {
    PeriodoLectivo save(PeriodoLectivo periodo);
    List<PeriodoLectivo> findAll();
    Optional<PeriodoLectivo> findById(Long id);
    
}
