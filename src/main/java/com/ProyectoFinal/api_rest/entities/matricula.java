package com.ProyectoFinal.api_rest.entities;

import jakarta.persistence.Entity;
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
@Table(name = "matriculas")
public class matricula {

    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "periodo_id")
    private perodoLectivo periodo;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private curso Curso;

    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private studient estudiante;
}
