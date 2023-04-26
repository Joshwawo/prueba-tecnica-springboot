package com.example.crudspring.repository;

import com.example.crudspring.entity.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Long> {

    Docente findByUsuario(String usuario);
}
