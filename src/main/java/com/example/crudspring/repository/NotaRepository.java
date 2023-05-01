package com.example.crudspring.repository;

import com.example.crudspring.entity.Curso;
import com.example.crudspring.entity.Estudiante;
import com.example.crudspring.entity.Nota;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {
    @EntityGraph(value = "Nota.estudiante")
    List<Nota> findAll();

    @EntityGraph(value = "Nota.estudiante")
    List<Nota> findAllByEstudiante_Nombre(String nombre);

    List<Nota> findByCurso(Curso curso);
    List<Nota> findByEstudiante(Estudiante estudiante);

    Nota findByCursoAndEstudiante(Curso curso, Estudiante estudiante);


}
