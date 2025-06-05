package com.ProyectoFinal.api_rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProyectoFinal.api_rest.entities.curso;

public interface cursoRepository extends JpaRepository<curso, Long>{
    
}
