package com.example.crudspring.controller;

import com.example.crudspring.entity.Estudiante;
import com.example.crudspring.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/estudiantes")
public class EstudianteController {
    @Autowired

    private EstudianteService estudianteService;

    @GetMapping("")
    public List<Estudiante> getEstudiantes(){
        return estudianteService.getEstudiantes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> getEstudiantePorId(@PathVariable("id") Long id){
       Estudiante estudiante = estudianteService.getEstudiante(id);

       if(estudiante != null){
           return new ResponseEntity<>(estudiante, HttpStatus.OK);
       }else {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }

    @PostMapping("")
    public ResponseEntity<Estudiante> crearEstudiante(@RequestBody Estudiante estudiante){
        Estudiante estudianteCreado = estudianteService.guardarEstudiante(estudiante);
        return new ResponseEntity<>(estudianteCreado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> actualizarEstudiante(@PathVariable("id") Long id, @RequestBody Estudiante estudiante){
        Estudiante estudianteActualizado = estudianteService.actualizarEstudiante(estudiante);
        return new ResponseEntity<>(estudianteActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<HttpStatus> eliminarEstudiante(@PathVariable("id") Long id){
       Estudiante estudiante = estudianteService.getEstudiante(id);
       if(estudiante != null){
           estudianteService.eliminarEstudiante(id);
           return new ResponseEntity<>(HttpStatus.OK);
         }else {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }
}
