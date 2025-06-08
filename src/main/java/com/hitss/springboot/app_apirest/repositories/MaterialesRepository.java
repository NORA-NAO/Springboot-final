package com.hitss.springboot.app_apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hitss.springboot.app_apirest.entities.Asignaturas;
import com.hitss.springboot.app_apirest.entities.Material;
import java.util.List;


public interface MaterialesRepository extends JpaRepository<Material,Long> {
    List<Material> findByAsignatura(Asignaturas asignatura);
}
