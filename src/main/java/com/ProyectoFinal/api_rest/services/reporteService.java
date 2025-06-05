package com.ProyectoFinal.api_rest.services;

import java.util.List;

import com.ProyectoFinal.api_rest.entities.matricula;
import com.ProyectoFinal.api_rest.entities.nota;

public interface reporteService {
    
    List<nota> promedioCurso(Long id);
    List<nota> promedioAsignatura(Long id);
    List<matricula> findAll(Long id); 

}

