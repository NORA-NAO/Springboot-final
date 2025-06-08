package com.hitss.springboot.app_apirest.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
public class Material {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Asignaturas asignatura;

    @ManyToOne
    @JoinColumn(name = "id_profesor")
    private Professor profesor;
}
