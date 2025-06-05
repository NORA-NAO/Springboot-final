package com.ProyectoFinal.api_rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProyectoFinal.api_rest.entities.nota;

public interface notaRepository extends JpaRepository<nota,Long> {
    
}
