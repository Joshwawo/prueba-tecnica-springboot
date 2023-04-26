package com.example.crudspring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "tbl_docente", uniqueConstraints = {
        @UniqueConstraint(columnNames = "usuario"),
        @UniqueConstraint(columnNames = "correo")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Docente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEstudiante;
    @Column(nullable = false)
    private String  nombre;

    @Column(nullable = false)
    private String  apellido;

    @Column(unique = true, nullable = false)
    private String  usuario;

    @Column(nullable = true)
    @Transient
    private String  contrasena;

    @Column(nullable = false)
    private String  contrasenaEncriptada;


    @Column(unique = true, nullable = false)
    private String  correo;

    public void setContrasena(String contrasena) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.contrasena = passwordEncoder.encode(contrasena);
    }


    public void validarContrasena(String contrasena){
        new BCryptPasswordEncoder().matches(contrasena, this.contrasenaEncriptada);
    }

}
