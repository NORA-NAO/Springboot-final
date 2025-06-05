package com.ProyectoFinal.api_rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProyectoFinal.api_rest.entities.asignaturas;
import com.ProyectoFinal.api_rest.entities.nota;
import com.ProyectoFinal.api_rest.entities.studient;

import java.util.List;


public interface notaRepository extends JpaRepository<nota,Long> {
    List<nota> findByAsignatura(asignaturas asignatura);
    List<nota> findByEstudiante(studient estudiante);
}
