package com.ProyectoFinal.api_rest.services;

import java.util.List;
import java.util.Optional;

import com.ProyectoFinal.api_rest.entities.user;

public interface userService {
    List<user> findAll();
    Optional<user> findById(Long id);
    user save(user usuario);
    Optional<user> update(Long id, user usuario);
    Optional<user> delete(Long id);
    Optional<user> findByEmail(String email);
}
