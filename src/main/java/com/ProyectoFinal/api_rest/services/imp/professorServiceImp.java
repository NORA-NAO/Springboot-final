package com.ProyectoFinal.api_rest.services.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ProyectoFinal.api_rest.entities.Role;
import com.ProyectoFinal.api_rest.entities.asignaturas;
import com.ProyectoFinal.api_rest.entities.professor;
import com.ProyectoFinal.api_rest.entities.user;
import com.ProyectoFinal.api_rest.repositories.professorRepository;
import com.ProyectoFinal.api_rest.repositories.roleRepository;
import com.ProyectoFinal.api_rest.repositories.userRepository;
import com.ProyectoFinal.api_rest.services.professorService;

@Service
public class professorServiceImp implements professorService {

    @Autowired
    private professorRepository profesores;

    @Autowired
    private userRepository usuarios;

    @Autowired
    private roleRepository roles;

    @Override
    @Transactional(readOnly = true)
    public List<professor> findAll() {
        return profesores.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<professor> findById(Long id) {
        return profesores.findById(id);
    }

    @Override
    @Transactional
    public professor save(professor profesor) {
        user usuarioProfe = profesor.getUsuario();
        if (usuarioProfe.getId() == null){
            Optional<Role> roleOptional = roles.findByName("ROLE_PROFESSOR");
            if (roleOptional.isPresent())
                usuarioProfe.setRol(roleOptional.get());
            usuarioProfe = usuarios.save(usuarioProfe);
        }
        profesor.setUsuario(usuarioProfe);
        return profesores.save(profesor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<asignaturas> allAsignaturas(Long id) {
        Optional<professor> optionalProfessor = profesores.findById(id);
        if (optionalProfessor.isPresent()){
            professor profesorDB = optionalProfessor.orElseThrow();
            return profesorDB.getAsignaturas();
        }
        return List.of();
    }
    
}
