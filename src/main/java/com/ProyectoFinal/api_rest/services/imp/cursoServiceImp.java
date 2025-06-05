package com.ProyectoFinal.api_rest.services.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ProyectoFinal.api_rest.entities.curso;
import com.ProyectoFinal.api_rest.repositories.cursoRepository;
import com.ProyectoFinal.api_rest.services.cursoService;

@Service
public class cursoServiceImp implements cursoService {

    @Autowired
    private cursoRepository cursos;

    @Override
    @Transactional
    public curso save(curso c) {
        return cursos.save(c);
    }

    @Override
    @Transactional(readOnly = true)
    public List<curso> findAll() {
        return cursos.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<curso> findById(Long id) {
        return cursos.findById(id);
    }
    
}
