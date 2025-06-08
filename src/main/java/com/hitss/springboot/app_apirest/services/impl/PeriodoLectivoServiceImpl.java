package com.hitss.springboot.app_apirest.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hitss.springboot.app_apirest.services.PeriodoLectivoService;
import com.hitss.springboot.app_apirest.repositories.PeriodoLectivoRepository;
import com.hitss.springboot.app_apirest.entities.PeriodoLectivo;

@Service
public class PeriodoLectivoServiceImpl implements PeriodoLectivoService {
    @Autowired
    private PeriodoLectivoRepository periodos;

    @Override
    @Transactional
    public PeriodoLectivo save(PeriodoLectivo periodo) {
        return periodos.save(periodo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PeriodoLectivo> findAll() {
        return periodos.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PeriodoLectivo> findById(Long id) {
        return periodos.findById(id);
    }
}
