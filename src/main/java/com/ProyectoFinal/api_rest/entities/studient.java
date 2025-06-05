package com.ProyectoFinal.api_rest.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "estudiantes")

public class studient {

    @Id
    private Long id;
    @OneToOne
    @JoinColumn(name = "id_user")
    private user usuario;
    @NotNull
    private Long matricula;
    @NotBlank
    private String actualCurse;
    
}
