package com.hitss.springboot.app_apirest.services;

import java.util.Optional;
import java.util.List;

import com.hitss.springboot.app_apirest.entities.Material;

public interface MaterialService {
    Material save(Material m);
    List<Material> findByAssignature(Long id);
    Optional<Material> delete(Long id);
    
}
