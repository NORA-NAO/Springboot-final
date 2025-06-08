package com.hitss.springboot.app_apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hitss.springboot.app_apirest.entities.Asignaturas;
import com.hitss.springboot.app_apirest.entities.Nota;
import com.hitss.springboot.app_apirest.entities.Student;

import java.util.List;


public interface NotaRepository extends JpaRepository<Nota,Long> {
    List<Nota> findByAsignatura(Asignaturas asignatura);
    List<Nota> findByEstudiante(Student estudiante);
}
