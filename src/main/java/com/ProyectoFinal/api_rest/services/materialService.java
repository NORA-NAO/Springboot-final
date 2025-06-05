package com.ProyectoFinal.api_rest.services;

import java.util.List;
import java.util.Optional;

import com.ProyectoFinal.api_rest.entities.material;

public interface materialService {
    material save(material m);
    List<material> findByAssignature(Long id);
    Optional<material> delete(Long id);
}
