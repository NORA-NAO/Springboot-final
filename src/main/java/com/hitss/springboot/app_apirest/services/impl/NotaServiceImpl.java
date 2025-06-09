package com.hitss.springboot.app_apirest.services.impl;

import java.math.BigDecimal;
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
import com.hitss.springboot.app_apirest.services.impl.dto.PromedioAsignaturaCursoDTO;
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
        PeriodoLectivo periodo = Note.getPeriodo();
        if (periodo.getId() == null)
            periodo = periodos.save(periodo);
        Asignaturas asig = Note.getAsignatura();
        if (asig.getId() == null)
            asig = asigs.save(asig);
        Student estudiante = Note.getEstudiante();
        if (estudiante.getId() == null)
            estudiante = estudiantes.save(estudiante);
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
