package com.ProyectoFinal.api_rest.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="asignaturas")
public class asignaturas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    private String nombre_Asignatura;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_professor")
    private professor profesor;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private curso Curso;
}
