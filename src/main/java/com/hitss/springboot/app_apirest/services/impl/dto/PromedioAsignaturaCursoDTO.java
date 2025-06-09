package com.hitss.springboot.app_apirest.services.impl.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PromedioAsignaturaCursoDTO {
    private String asignatura;
    private String curso;
    private BigDecimal promedio;
}