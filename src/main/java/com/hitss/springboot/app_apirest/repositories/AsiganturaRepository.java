package com.hitss.springboot.app_apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hitss.springboot.app_apirest.entities.Asignaturas;

public interface AsiganturaRepository extends JpaRepository<Asignaturas,Long>{
    
}
