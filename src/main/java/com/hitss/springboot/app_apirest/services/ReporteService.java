package com.hitss.springboot.app_apirest.services;

import com.hitss.springboot.app_apirest.entities.Nota;
import com.hitss.springboot.app_apirest.services.impl.dto.PromedioAsignaturaCursoDTO;

import java.util.List;

import com.hitss.springboot.app_apirest.entities.Matricula;

public interface ReporteService {
    List<Nota> promedioCurso(Long id);
    List<Nota> promedioAsignatura(Long id);
    List<Matricula> findAll(Long id); 
    List<PromedioAsignaturaCursoDTO> obtenerPromedios();
}
