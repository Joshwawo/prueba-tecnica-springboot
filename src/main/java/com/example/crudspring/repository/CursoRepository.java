package com.example.crudspring.repository;

import com.example.crudspring.entity.Curso;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
   // @EntityGraph(attributePaths = {"estudiantes"})
}
