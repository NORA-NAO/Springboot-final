package com.hitss.springboot.app_apirest.services.impl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class calificacionesDTO {
    private String asignatura;
    private Integer valor;
}