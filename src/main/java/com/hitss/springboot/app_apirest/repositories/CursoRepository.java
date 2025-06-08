package com.hitss.springboot.app_apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hitss.springboot.app_apirest.entities.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{
    
}
