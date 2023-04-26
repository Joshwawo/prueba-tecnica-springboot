package com.example.crudspring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Matriculacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMatriculacion;

    @ManyToOne
    @JoinColumn(name = "idEstudiante", nullable = false)
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "idCurso", nullable = false)
    private Curso curso;
}
