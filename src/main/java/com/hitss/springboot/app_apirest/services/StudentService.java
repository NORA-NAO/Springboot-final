package com.hitss.springboot.app_apirest.services;

import java.util.List;
import java.util.Optional;

import com.hitss.springboot.app_apirest.entities.Nota;
import com.hitss.springboot.app_apirest.entities.Student;

public interface StudentService {
    Student save(Student estudiante);
    List<Student> findAll();
    Optional<Student> findById(Long id);
    List<Nota> findAllNotas(Long id);
    
}
