package com.ProyectoFinal.api_rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProyectoFinal.api_rest.entities.material;

public interface materialesRepository extends JpaRepository<material,Long> {
    
}
