package com.ProyectoFinal.api_rest.entities;

import org.springframework.security.core.userdetails.User;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "estudiantes")
public class studient {

    @OneToOne
    @JoinColumn(name = "id_user")
    private user usuario;
    private Long matricula;
    private String actualCurse;
    
}
