package com.hitss.springboot.app_apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hitss.springboot.app_apirest.services.CursoService;
import com.hitss.springboot.app_apirest.services.NotaService;
import com.hitss.springboot.app_apirest.services.ReporteService;
import com.hitss.springboot.app_apirest.services.impl.dto.PromedioAsignaturaCursoDTO;
import com.hitss.springboot.app_apirest.services.impl.dto.calificacionesDTO;
import com.hitss.springboot.app_apirest.services.impl.dto.cursoDetalleDTO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/reportes")
public class ReportesController {
    
    @Autowired
    private ReporteService reporteService;
    @Autowired
    private NotaService notaService;
    @Autowired
    private CursoService cursoService;

    @GetMapping("/notas-promedio")
    public List<PromedioAsignaturaCursoDTO> obtenerPromedios() {
        return reporteService.obtenerPromedios();
    }

    @GetMapping("/historial-estudiante/{id}")
    public List<calificacionesDTO> calificaciones(@PathVariable Integer id) {
        return notaService.findAllValues(id);    
    }

    @GetMapping("/reporte-final/{cursoId}")
    public List<cursoDetalleDTO> cursoDetalle(@PathVariable Integer cursoId) {
        return cursoService.findValuesFromCurso(cursoId);
    }
    

}
