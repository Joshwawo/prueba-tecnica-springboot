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
    private Integer idDocente;
    @Column(nullable = false)
    private String  nombre;

    @Column(nullable = false)
    private String  apellido;

    @Column(unique = true, nullable = false)
    private String  usuario;

    @Column(nullable = true)
    private String  contrasena;

    @Column(unique = true, nullable = false)
    private String  correo;

    public void setPassword(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.contrasena = passwordEncoder.encode(password);
    }


}
