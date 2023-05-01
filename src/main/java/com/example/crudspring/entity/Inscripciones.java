package com.example.crudspring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_inscripciones")
public class Inscripciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idInscripcion;

    @ManyToOne
    @JoinColumn(name = "idEstudiante", nullable = false)
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "idCurso", nullable = false)
    private Curso curso;

    private BigDecimal califPractica;
    private BigDecimal califParcial;
    private BigDecimal califExamen;
    private BigDecimal califFinal;

}
