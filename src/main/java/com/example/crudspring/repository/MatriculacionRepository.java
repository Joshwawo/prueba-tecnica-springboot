package com.example.crudspring.repository;

import com.example.crudspring.entity.Matriculacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculacionRepository extends JpaRepository<Matriculacion, Long> {
}
