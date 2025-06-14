package com.hitss.springboot.app_apirest.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "notas")
public class Nota {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "periodo_id")
    private PeriodoLectivo periodo;

    @ManyToOne
    @JoinColumn(name = "asignatura_id")
    private Asignaturas asignatura;

    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Student estudiante;

    @NotNull
    @Min(value = 0)
    @Max(value = 10)
    private Integer valor;

    @NotBlank
    private String observaciones;
}
