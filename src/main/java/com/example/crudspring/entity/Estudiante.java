package com.example.crudspring.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_estudiante", uniqueConstraints = {
        @UniqueConstraint(columnNames = "usuario"),
        @UniqueConstraint(columnNames = "correo")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstudiante;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(unique = true, nullable = false)
    private String usuario;

    @Column(nullable = false)
    private String contrasena;

    @Column(unique = true, nullable = false)
    private String correo;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Curso> cursos = new ArrayList<>();

}
