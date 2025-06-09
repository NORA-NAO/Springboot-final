package com.hitss.springboot.app_apirest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hitss.springboot.app_apirest.entities.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    @Query(value = """
                SELECT c.name AS Curso,
                        a.nombre_asignatura AS Asignatura,
                        n.valor AS Notas
                FROM notas n
                JOIN asignaturas a ON n.asignatura_id = a.id
                JOIN cursos c ON a.curso_id = c.id
                WHERE c.id=?1;
                """, nativeQuery = true)
    List<Object[]> findValuesFromCurso(Integer id);

}
