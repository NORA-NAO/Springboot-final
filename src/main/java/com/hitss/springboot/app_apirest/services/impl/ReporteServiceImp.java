package com.hitss.springboot.app_apirest.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hitss.springboot.app_apirest.entities.Matricula;
import com.hitss.springboot.app_apirest.entities.Nota;
import com.hitss.springboot.app_apirest.repositories.NotaRepository;
import com.hitss.springboot.app_apirest.services.ReporteService;
import com.hitss.springboot.app_apirest.services.impl.dto.PromedioAsignaturaCursoDTO;

@Service
public class ReporteServiceImp implements ReporteService {
    @Autowired
    private NotaRepository notaRepository;

    @Override
    public List<Nota> promedioCurso(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'promedioCurso'");
    }

    @Override
    public List<Nota> promedioAsignatura(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'promedioAsignatura'");
    }

    @Override
    public List<Matricula> findAll(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public List<PromedioAsignaturaCursoDTO> obtenerPromedios() {
        List<Object[]> resultados = notaRepository.findPromediosAsignaturaYCurso();
        
        return resultados.stream()
            .map(row -> new PromedioAsignaturaCursoDTO(
                (String) row[0],  // asignatura
                (String) row[1],  // curso
                (BigDecimal) row[2]   // promedio
            ))
            .collect(Collectors.toList());
    }
}
