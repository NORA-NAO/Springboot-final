package com.hitss.springboot.app_apirest.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hitss.springboot.app_apirest.services.MaterialService;
import com.hitss.springboot.app_apirest.services.AsignaturaService;
import com.hitss.springboot.app_apirest.services.ProfessorService;
import com.hitss.springboot.app_apirest.repositories.MaterialesRepository;
import com.hitss.springboot.app_apirest.entities.Material;
import com.hitss.springboot.app_apirest.entities.Professor;
import com.hitss.springboot.app_apirest.entities.Asignaturas;

@Service
public class MaterialServiceImpl implements MaterialService {
    
    @Autowired
    private MaterialesRepository materiales;

    @Autowired
    private AsignaturaService asignaturas;

    @Autowired
    private ProfessorService profesores;

    @Override
    @Transactional
    public Material save(Material m) {
        Asignaturas asig = m.getAsignatura();
        if (asig.getId() == null)
            asig = asignaturas.save(asig);
        Professor profe = m.getProfesor();
        if (profe.getId() == null)
            profe = profesores.save(profe);
        m.setAsignatura(asig);
        m.setProfesor(profe);
        return materiales.save(m);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Material> findByAssignature(Long id) {
        Optional<Asignaturas> asig = asignaturas.findById(id);
        if (asig.isPresent())
            return materiales.findByAsignatura(asig.get());
        return List.of();
    }

    @Override
    @Transactional
    public Optional<Material> delete(Long id) {
        Optional<Material> optionalMaterial = materiales.findById(id);
        optionalMaterial.ifPresent(materiales::delete);
        return optionalMaterial;
    }
}
