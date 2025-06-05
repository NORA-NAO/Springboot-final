package com.ProyectoFinal.api_rest.services.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ProyectoFinal.api_rest.entities.asignaturas;
import com.ProyectoFinal.api_rest.entities.curso;
import com.ProyectoFinal.api_rest.entities.professor;
import com.ProyectoFinal.api_rest.repositories.asiganturaRepository;
import com.ProyectoFinal.api_rest.services.asignaturaService;
import com.ProyectoFinal.api_rest.services.cursoService;
import com.ProyectoFinal.api_rest.services.professorService;

@Service
public class asignaturaServiceImp implements asignaturaService {

    @Autowired
    private asiganturaRepository asigs;

    @Autowired
    private professorService profesores;

    @Autowired
    private cursoService cursos;

    @Override
    @Transactional
    public asignaturas save(asignaturas asig) {
        professor profe = asig.getProfesor();
        if (profe.getId() == null)
            profe = profesores.save(profe);
        curso c = asig.getCurso();
        if (c.getId() == null)
            c = cursos.save(c);
        asig.setProfesor(profe);
        asig.setCurso(c);
        return asigs.save(asig);

    }

    @Override
    @Transactional(readOnly = true)
    public List<asignaturas> findAll() {
        return asigs.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<asignaturas> findById(Long id) {
        return asigs.findById(id);
    }

    @Override
    @Transactional
    public Optional<asignaturas> update(Long id, asignaturas as) {
        Optional<asignaturas> optionalAsig = asigs.findById(id);
        if (optionalAsig.isPresent()){
            asignaturas asignaturaDB = optionalAsig.orElseThrow();
            asignaturaDB.setNombre_Asignatura(as.getNombre_Asignatura());
            asignaturaDB.setProfesor(as.getProfesor());
            asignaturaDB.setCurso(as.getCurso());
            return Optional.of(asigs.save(asignaturaDB));
        }
        return optionalAsig;
    }

    @Override
    @Transactional
    public Optional<asignaturas> delete(Long id) {
        Optional<asignaturas> optionalAsig = asigs.findById(id);
        optionalAsig.ifPresent(asigs::delete);
        return optionalAsig;
    }
    
}
