package com.ProyectoFinal.api_rest.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @Size(min = 4, max = 100)
    private String titulo;
    @NotBlank
     @Size(min = 4, max = 160)
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
