package com.hitss.springboot.app_apirest.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hitss.springboot.app_apirest.services.NotaService;
import com.hitss.springboot.app_apirest.services.PeriodoLectivoService;
import com.hitss.springboot.app_apirest.services.AsignaturaService;
import com.hitss.springboot.app_apirest.services.StudentService;
import com.hitss.springboot.app_apirest.services.impl.dto.calificacionesDTO;
import com.hitss.springboot.app_apirest.repositories.NotaRepository;
import com.hitss.springboot.app_apirest.entities.Nota;
import com.hitss.springboot.app_apirest.entities.PeriodoLectivo;
import com.hitss.springboot.app_apirest.entities.Asignaturas;
import com.hitss.springboot.app_apirest.entities.Student;

@Service
public class NotaServiceImpl implements NotaService {

    @Autowired
    private NotaRepository notas;

    @Autowired
    private PeriodoLectivoService periodos;

    @Autowired
    private AsignaturaService asigs;

    @Autowired
    private StudentService estudiantes;

    @Override
    @Transactional
    public Nota save(Nota Note) {
        Long periodoId = Note.getPeriodo().getId();
        PeriodoLectivo periodo = periodos.findById(periodoId).orElseThrow();
        Long asigId = Note.getAsignatura().getId();
        Asignaturas asig = asigs.findById(asigId).orElseThrow();
        Long estudianteId = Note.getEstudiante().getId();
        Student estudiante = estudiantes.findById(estudianteId).orElseThrow();
        Note.setPeriodo(periodo);
        Note.setAsignatura(asig);
        Note.setEstudiante(estudiante);
        return notas.save(Note);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Nota> findByAsignatura(Asignaturas asignaturas) {
        return notas.findByAsignatura(asignaturas);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Nota> findByEstudiante(Student estudiante) {
        return notas.findByEstudiante(estudiante);
    }

    @Override
    @Transactional
    public Optional<Nota> update(Long id, Nota note) {
        Optional<Nota> optionalNote = notas.findById(id);
        if (optionalNote.isPresent()){
            Nota notaBD = optionalNote.orElseThrow();
            Long periodoId = note.getPeriodo().getId();
            PeriodoLectivo periodo = periodos.findById(periodoId).orElseThrow();
            Long asigId = note.getAsignatura().getId();
            Asignaturas asig = asigs.findById(asigId).orElseThrow();
            Long estudianteId = note.getEstudiante().getId();
            Student estudiante = estudiantes.findById(estudianteId).orElseThrow();
            notaBD.setPeriodo(periodo);
            notaBD.setAsignatura(asig);
            notaBD.setEstudiante(estudiante);
            notaBD.setValor(note.getValor());
            notaBD.setObservaciones(note.getObservaciones());
            return Optional.of(notas.save(notaBD));
        }
        return optionalNote;
    }

    @Override
    @Transactional
    public Optional<Nota> delete(Long id) {
        Optional<Nota> optionalNota = notas.findById(id);
        optionalNota.ifPresent(notas::delete);
        return optionalNota;
    }

    @Override
    public List<calificacionesDTO> findAllValues(Integer id) {
        List<Object[]> resultados = notas.findAllValues(id);
        return resultados.stream()
            .map(row -> new calificacionesDTO(
                (String) row[0],  // asignatura
                (Integer) row[1]  // curso
            ))
            .collect(Collectors.toList());
    }
}
