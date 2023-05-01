package com.example.crudspring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_curso", uniqueConstraints = {
        @UniqueConstraint(columnNames = "nombre")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long idCurso;

    @Column(nullable = false)
    private  String  nombre;

    private String  descripcion;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "cursos")
    private List<Estudiante> estudiantes = new ArrayList<>();

}
