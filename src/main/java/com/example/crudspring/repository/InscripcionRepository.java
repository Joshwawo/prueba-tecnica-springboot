package com.example.crudspring.repository;

import com.example.crudspring.entity.Curso;
import com.example.crudspring.entity.Estudiante;
import com.example.crudspring.entity.Inscripciones;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InscripcionRepository extends JpaRepository<Inscripciones, Long> {
    List<Inscripciones> findByEstudiante(Estudiante estudiante);

    List<Inscripciones> findByCurso(Curso curso);

    Inscripciones findByEstudianteAndCurso(Estudiante estudiante, Curso curso);
}
