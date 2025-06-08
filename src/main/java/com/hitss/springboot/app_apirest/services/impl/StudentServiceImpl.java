package com.hitss.springboot.app_apirest.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hitss.springboot.app_apirest.services.StudentService;
import com.hitss.springboot.app_apirest.repositories.UserRepository;
import com.hitss.springboot.app_apirest.repositories.StudentRepository;
import com.hitss.springboot.app_apirest.repositories.NotaRepository;
import com.hitss.springboot.app_apirest.repositories.RoleRepository;
import com.hitss.springboot.app_apirest.entities.Student;
import com.hitss.springboot.app_apirest.entities.User;
import com.hitss.springboot.app_apirest.entities.Role;
import com.hitss.springboot.app_apirest.entities.Nota;

@Service
public class StudentServiceImpl implements StudentService {
    
    @Autowired
    private StudentRepository estudiantes;

    @Autowired
    private UserRepository usuarios;

    @Autowired
    private NotaRepository notas;

    @Autowired
    private RoleRepository roles;

    @Override
    @Transactional
    public Student save(Student estudiante) {
        User usuarioEstudiante = estudiante.getUser();
        List<Role> roles_estudiante = new ArrayList<>();
        if (usuarioEstudiante.getId() == null) {
            Optional<Role> roleOptional = roles.findByName("ROLE_STUDENT");
            roleOptional.ifPresent(roles_estudiante::add);
            usuarioEstudiante.setRoles(roles_estudiante);
            usuarioEstudiante = usuarios.save(usuarioEstudiante);
        }
        estudiante.setUser(usuarioEstudiante);
        return estudiantes.save(estudiante);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> findAll() {
        return estudiantes.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Student> findById(Long id) {
        return estudiantes.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Nota> findAllNotas(Long id) {
        Optional<Student> optionalEstudiante = estudiantes.findById(id);
        if (optionalEstudiante.isPresent()) {
            Student estudianteDB = optionalEstudiante.orElseThrow();
            return notas.findByEstudiante(estudianteDB);
        }
        return List.of();
    }
}
