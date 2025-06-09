package com.hitss.springboot.app_apirest.services;

import java.util.List;
import java.util.Optional;

import com.hitss.springboot.app_apirest.entities.Curso;
import com.hitss.springboot.app_apirest.services.impl.dto.cursoDetalleDTO;

public interface CursoService {
    Curso save(Curso c);
    List<Curso> findAll();
    Optional<Curso> findById(Long id);
    List<cursoDetalleDTO> findValuesFromCurso(Integer id);
}
