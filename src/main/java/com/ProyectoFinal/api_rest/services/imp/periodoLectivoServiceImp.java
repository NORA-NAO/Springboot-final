package com.ProyectoFinal.api_rest.services.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ProyectoFinal.api_rest.entities.periodoLectivo;
import com.ProyectoFinal.api_rest.repositories.periodoLectivoRepository;
import com.ProyectoFinal.api_rest.services.periodoLectivoService;

@Service
public class periodoLectivoServiceImp implements periodoLectivoService {

    @Autowired
    private periodoLectivoRepository periodos;

    @Override
    @Transactional
    public periodoLectivo save(periodoLectivo periodo) {
        return periodos.save(periodo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<periodoLectivo> findAll() {
        return periodos.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<periodoLectivo> findById(Long id) {
        return periodos.findById(id);
    }
    
}
