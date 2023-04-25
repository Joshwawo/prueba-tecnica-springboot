package com.example.crudspring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permiso {
    @Id
    private Integer idPermiso;

    @ManyToOne
    @JoinColumn(name = "idDocente")
    private Docente docente;

    @ManyToOne
    @JoinColumn(name = "idEstudiante")
    private Estudiante estudiante;

    private boolean editar;
    private boolean eliminar;
    private boolean crear;
    private boolean leer;
}
