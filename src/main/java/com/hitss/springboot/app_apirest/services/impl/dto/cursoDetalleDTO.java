package com.hitss.springboot.app_apirest.services.impl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class cursoDetalleDTO {
    private String curso;
    private String asignatura;
    private Integer nota;
}
