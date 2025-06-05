package com.ProyectoFinal.api_rest.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "profesores")
public class professor {

    @Id
    private Long id;
    @OneToOne
    @JoinColumn(name = "id_user")
    private user usuario;
    private String especialidad;
    private String asignatura;
}
