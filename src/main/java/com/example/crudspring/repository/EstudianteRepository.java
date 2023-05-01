package com.example.crudspring.repository;

import com.example.crudspring.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

    Estudiante findByNombre(String nombre);

}
