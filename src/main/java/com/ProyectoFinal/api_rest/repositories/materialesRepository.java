package com.ProyectoFinal.api_rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProyectoFinal.api_rest.entities.asignaturas;
import com.ProyectoFinal.api_rest.entities.material;
import java.util.List;


public interface materialesRepository extends JpaRepository<material,Long> {
    List<material> findByAsignatura(asignaturas asignatura);
}
