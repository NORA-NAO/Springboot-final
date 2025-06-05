package com.ProyectoFinal.api_rest.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "materiales")
public class material {
    
    @Id
    private Long id;

    @NotBlank
    private String titulo;
    @NotBlank
    private String descripcion;
    @NotBlank
    private String archivo_url;

    @ManyToOne
    @JoinColumn(name = "asignatura_id")
    private asignaturas asignatura;

    @ManyToOne
    @JoinColumn(name = "id_professor")
    private professor profesor;
}
