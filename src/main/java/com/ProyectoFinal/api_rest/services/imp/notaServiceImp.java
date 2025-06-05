package com.ProyectoFinal.api_rest.services.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ProyectoFinal.api_rest.entities.asignaturas;
import com.ProyectoFinal.api_rest.entities.nota;
import com.ProyectoFinal.api_rest.entities.periodoLectivo;
import com.ProyectoFinal.api_rest.entities.studient;
import com.ProyectoFinal.api_rest.repositories.notaRepository;
import com.ProyectoFinal.api_rest.services.asignaturaService;
import com.ProyectoFinal.api_rest.services.notaService;
import com.ProyectoFinal.api_rest.services.periodoLectivoService;
import com.ProyectoFinal.api_rest.services.studientService;

@Service
public class notaServiceImp implements notaService {

    @Autowired
    private notaRepository notas;

    @Autowired
    private periodoLectivoService periodos;

    @Autowired
    private asignaturaService asigs;

    @Autowired
    private studientService estudiantes;

    @Override
    @Transactional
    public nota save(nota Note) {
        periodoLectivo periodo = Note.getPeriodo();
        if (periodo.getId() == null)
            periodo = periodos.save(periodo);
        asignaturas asig = Note.getAsignatura();
        if (asig.getId() == null)
            asig = asigs.save(asig);
        studient estudiante = Note.getEstudiante();
        if (estudiante.getId() == null)
            estudiante = estudiantes.save(estudiante);
        Note.setPeriodo(periodo);
        Note.setAsignatura(asig);
        Note.setEstudiante(estudiante);
        return notas.save(Note);
    }

    @Override
    @Transactional(readOnly = true)
    public List<nota> findByAssignature(Long id) {
        return findByAssignature(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<nota> findByStudent(Long id) {
        return findByStudent(id);
    }

    @Override
    @Transactional
    public Optional<nota> update(Long id, nota note) {
        Optional<nota> optionalNote = notas.findById(id);
        if (optionalNote.isPresent()){
            nota notaBD = optionalNote.orElseThrow();
            notaBD.setPeriodo(note.getPeriodo());
            notaBD.setAsignatura(note.getAsignatura());
            notaBD.setEstudiante(note.getEstudiante());
            notaBD.setValor(note.getValor());
            notaBD.setObservaciones(note.getObservaciones());
            return Optional.of(notas.save(notaBD));
        }
        return optionalNote;
    }

    @Override
    @Transactional
    public Optional<nota> delete(Long id) {
        Optional<nota> optionalNota = notas.findById(id);
        optionalNota.ifPresent(notas::delete);
        return optionalNota;
    }
    
}
