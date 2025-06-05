package com.ProyectoFinal.api_rest.services.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ProyectoFinal.api_rest.entities.asignaturas;
import com.ProyectoFinal.api_rest.entities.material;
import com.ProyectoFinal.api_rest.entities.professor;
import com.ProyectoFinal.api_rest.repositories.materialesRepository;
import com.ProyectoFinal.api_rest.services.asignaturaService;
import com.ProyectoFinal.api_rest.services.materialService;
import com.ProyectoFinal.api_rest.services.professorService;

@Service
public class materialServiceImp implements materialService {

    @Autowired
    private materialesRepository materiales;

    @Autowired
    private asignaturaService asignaturas;

    @Autowired
    private professorService profesores;

    @Override
    @Transactional
    public material save(material m) {
        asignaturas asig = m.getAsignatura();
        if (asig.getId() == null)
            asig = asignaturas.save(asig);
        professor profe = m.getProfesor();
        if (profe.getId() == null)
            profe = profesores.save(profe);
        m.setAsignatura(asig);
        m.setProfesor(profe);
        return materiales.save(m);
    }

    @Override
    @Transactional(readOnly = true)
    public List<material> findByAssignature(Long id) {
        Optional<asignaturas> asig = asignaturas.findById(id);
        if (asig.isPresent())
            return materiales.findByAsignatura(asig.get());
        return List.of();
    }

    @Override
    @Transactional
    public Optional<material> delete(Long id) {
        Optional<material> optionalMaterial = materiales.findById(id);
        optionalMaterial.ifPresent(materiales::delete);
        return optionalMaterial;
    }
    
}
