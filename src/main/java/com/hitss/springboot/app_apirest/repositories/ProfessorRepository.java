package com.hitss.springboot.app_apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hitss.springboot.app_apirest.entities.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    
}
