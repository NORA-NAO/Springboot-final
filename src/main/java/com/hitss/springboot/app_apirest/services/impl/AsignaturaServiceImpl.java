package com.hitss.springboot.app_apirest.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hitss.springboot.app_apirest.repositories.AsiganturaRepository;
import com.hitss.springboot.app_apirest.services.AsignaturaService;
import com.hitss.springboot.app_apirest.services.CursoService;
import com.hitss.springboot.app_apirest.services.ProfessorService;
import com.hitss.springboot.app_apirest.entities.Professor;
import com.hitss.springboot.app_apirest.entities.Asignaturas;
import com.hitss.springboot.app_apirest.entities.Curso;


@Service
public class AsignaturaServiceImpl implements AsignaturaService {
    
    @Autowired
    private AsiganturaRepository asigs;

    @Autowired
    private ProfessorService profesores;

    @Autowired
    private CursoService cursos;

    @Override
    @Transactional
    public Asignaturas save(Asignaturas asig) {
        Long profeId = asig.getProfesor().getId();
        Professor profe = profesores.findById(profeId).orElseThrow();
        Long cursoId = asig.getCurso().getId();
        Curso c = cursos.findById(cursoId).orElseThrow();
        asig.setProfesor(profe);
        asig.setCurso(c);
        return asigs.save(asig);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Asignaturas> findAll() {
        return asigs.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Asignaturas> findById(Long id) {
        return asigs.findById(id);
    }

    @Override
    @Transactional
    public Optional<Asignaturas> update(Long id, Asignaturas as) {
        Optional<Asignaturas> optionalAsig = asigs.findById(id);
        if (optionalAsig.isPresent()){
            Asignaturas asignaturaDB = optionalAsig.orElseThrow();
            asignaturaDB.setNombre_Asignatura(as.getNombre_Asignatura());
            Professor profe = profesores.findById(as.getProfesor().getId()).orElseThrow();
            asignaturaDB.setProfesor(profe);
            Curso c = cursos.findById(as.getCurso().getId()).orElseThrow();
            asignaturaDB.setCurso(c);
            return Optional.of(asigs.save(asignaturaDB));
        }
        return optionalAsig;
    }

    @Override
    @Transactional
    public Optional<Asignaturas> delete(Long id) {
        Optional<Asignaturas> optionalAsig = asigs.findById(id);
        optionalAsig.ifPresent(asigs::delete);
        return optionalAsig;
    }
}
