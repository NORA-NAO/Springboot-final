package com.ProyectoFinal.api_rest.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ProyectoFinal.api_rest.entities.Role;
@Repository
public interface roleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name);
}
