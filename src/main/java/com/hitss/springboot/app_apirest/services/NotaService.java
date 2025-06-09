package com.hitss.springboot.app_apirest.services;

import java.util.List;
import java.util.Optional;

import com.hitss.springboot.app_apirest.entities.Asignaturas;
import com.hitss.springboot.app_apirest.entities.Nota;
import com.hitss.springboot.app_apirest.entities.Student;
import com.hitss.springboot.app_apirest.services.impl.dto.calificacionesDTO;

public interface NotaService {
    Nota save(Nota note);
    List<Nota> findByAsignatura(Asignaturas asignatura);
    List<Nota> findByEstudiante(Student estudiante);
    Optional<Nota> update(Long id, Nota note);
    Optional<Nota> delete(Long id);
    List<calificacionesDTO> findAllValues(Integer id);


}
