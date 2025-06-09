package com.hitss.springboot.app_apirest.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hitss.springboot.app_apirest.services.CursoService;
import com.hitss.springboot.app_apirest.services.impl.dto.cursoDetalleDTO;
import com.hitss.springboot.app_apirest.repositories.CursoRepository;
import com.hitss.springboot.app_apirest.entities.Curso;

@Service
public class CursoServiceImpl implements CursoService {
    
    @Autowired
    private CursoRepository cursos;

    @Override
    @Transactional
    public Curso save(Curso c) {
        return cursos.save(c);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Curso> findAll() {
        return cursos.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> findById(Long id) {
        return cursos.findById(id);
    }

    @Override
    public List<cursoDetalleDTO> findValuesFromCurso(Integer id) {
       List<Object[]> resultados = cursos.findValuesFromCurso(id);
        return resultados.stream()
            .map(row -> new cursoDetalleDTO(
                (String) row[0],  // asignatura
                (String) row[1],
                (Integer) row[2]  // curso
            ))
            .collect(Collectors.toList());
    }
}
