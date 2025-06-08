package com.ProyectoFinal.api_rest.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ProyectoFinal.api_rest.entities.*;
import java.util.List;
import java.util.Optional;


public interface userRepository extends JpaRepository<user,Long> {
    Optional<user> findByEmail(String email);
}
