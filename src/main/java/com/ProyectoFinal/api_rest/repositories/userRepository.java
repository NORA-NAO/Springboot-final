package com.ProyectoFinal.api_rest.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ProyectoFinal.api_rest.entities.*;

public interface userRepository extends JpaRepository<user,Long> {
    
}
