package com.ProyectoFinal.api_rest.services.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ProyectoFinal.api_rest.entities.nota;
import com.ProyectoFinal.api_rest.entities.studient;
import com.ProyectoFinal.api_rest.entities.user;
import com.ProyectoFinal.api_rest.repositories.notaRepository;
import com.ProyectoFinal.api_rest.repositories.studientRepository;
import com.ProyectoFinal.api_rest.repositories.userRepository;
import com.ProyectoFinal.api_rest.services.studientService;

@Service
public class studentServiceImp implements studientService {

    @Autowired
    private studientRepository estudiantes;

    @Autowired
    private userRepository usuarios;
    
    @Autowired
    private notaRepository notas;

    @Override
    @Transactional
    public studient save(studient estudiante) {
        user usuarioEstudiante = estudiante.getUsuario();
        if (usuarioEstudiante.getId() == null){
            usuarioEstudiante.setEmail("Estudiante");
            usuarioEstudiante = usuarios.save(usuarioEstudiante);
        }
        estudiante.setUsuario(usuarioEstudiante);
        return estudiantes.save(estudiante);
    }

    @Override
    @Transactional(readOnly = true)
    public List<studient> findAll() {
        return estudiantes.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<studient> findById(Long id) {
        return estudiantes.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<nota> findAllNotas(Long id) {
        Optional<studient> optionalEstudiante = estudiantes.findById(id);
        if (optionalEstudiante.isPresent()){
            studient estudianteDB = optionalEstudiante.orElseThrow();
            return notas.findByEstudiante(estudianteDB);
        }
        return List.of();
    }
    
}
