package com.hitss.springboot.app_apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hitss.springboot.app_apirest.entities.Asignaturas;
import com.hitss.springboot.app_apirest.entities.Nota;
import com.hitss.springboot.app_apirest.entities.Student;

import java.util.List;

public interface NotaRepository extends JpaRepository<Nota, Long> {
    List<Nota> findByAsignatura(Asignaturas asignatura);

    List<Nota> findByEstudiante(Student estudiante);

    @Query(value = """
            SELECT a.nombre_asignatura AS asignatura,
                   c.name AS curso,
                   AVG(n.valor) AS promedio
            FROM notas n
            JOIN asignaturas a ON n.asignatura_id = a.id
            JOIN cursos c ON a.curso_id = c.id
            GROUP BY a.nombre_asignatura, c.name
            """, nativeQuery = true)
    List<Object[]> findPromediosAsignaturaYCurso();

    @Query(value = """
                SELECT a.nombre_asignatura AS asignatura,
                    n.valor AS valor
                FROM notas n
                JOIN asignaturas a ON n.asignatura_id = a.id
                WHERE n.estudiante_id = ?1
                ORDER BY a.nombre_asignatura
                """, nativeQuery = true)
    List<Object[]> findAllValues(Integer id);

}
