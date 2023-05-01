package com.example.crudspring.repository;

import com.example.crudspring.entity.Curso;
import com.example.crudspring.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
   // @EntityGraph(attributePaths = {"estudiantes"})

    Curso findByNombre(String nombre);

    Curso findByIdCurso(Integer idCurso);

    Curso findByNombreAndIdCursoNot(String nombre, Integer idCurso);

    Curso findByNombreAndIdCursoNotAndIdCursoNot(String nombre, Integer idCurso, Integer idCurso2);

    //Agregar estudiantes al curso

}
