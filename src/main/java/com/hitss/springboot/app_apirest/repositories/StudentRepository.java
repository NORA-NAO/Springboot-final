package com.hitss.springboot.app_apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hitss.springboot.app_apirest.entities.Student;

public interface StudentRepository extends JpaRepository<Student,Long>{
    
}
