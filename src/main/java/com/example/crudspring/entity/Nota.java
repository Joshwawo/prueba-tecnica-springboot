package com.example.crudspring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@NamedEntityGraph(name = "Nota.estudiante",
        attributeNodes = {
                @NamedAttributeNode("estudiante"),
                @NamedAttributeNode("curso")
        }
)

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNota;

    @ManyToOne
    @JoinColumn(name = "idEstudiante")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "idCurso")
    private Curso curso;

    private BigDecimal nota;
}
