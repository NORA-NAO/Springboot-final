package com.hitss.springboot.app_apirest.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hitss.springboot.app_apirest.services.ProfessorService;
import com.hitss.springboot.app_apirest.repositories.RoleRepository;
import com.hitss.springboot.app_apirest.repositories.UserRepository;
import com.hitss.springboot.app_apirest.repositories.ProfessorRepository;
import com.hitss.springboot.app_apirest.entities.Professor;
import com.hitss.springboot.app_apirest.entities.User;
import com.hitss.springboot.app_apirest.entities.Role;
import com.hitss.springboot.app_apirest.entities.Asignaturas;

@Service
public class ProfessorServiceImpl implements ProfessorService {
    @Autowired
    private ProfessorRepository profesores;

    @Autowired
    private UserRepository usuarios;

    @Autowired
    private RoleRepository roles;

    @Override
    @Transactional(readOnly = true)
    public List<Professor> findAll() {
        return profesores.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Professor> findById(Long id) {
        return profesores.findById(id);
    }

    @Override
    @Transactional
    public Professor save(Professor profesor) {
        User usuarioProfe = profesor.getUsuario();
        List<Role> roles_profe = new ArrayList<>();
        if (usuarioProfe.getId() == null){
            Optional<Role> roleOptional = roles.findByName("ROLE_PROFESSOR");
            roleOptional.ifPresent(roles_profe::add);
            usuarioProfe.setRoles(roles_profe);
            usuarioProfe = usuarios.save(usuarioProfe);
        }
        profesor.setUsuario(usuarioProfe);
        return profesores.save(profesor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Asignaturas> allAsignaturas(Long id) {
        Optional<Professor> optionalProfessor = profesores.findById(id);
        if (optionalProfessor.isPresent()){
            Professor profesorDB = optionalProfessor.orElseThrow();
            return profesorDB.getAsignaturas();
        }
        return List.of();
    }
}
