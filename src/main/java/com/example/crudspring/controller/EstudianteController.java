package com.example.crudspring.controller;

import com.example.crudspring.entity.Estudiante;
import com.example.crudspring.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public ResponseEntity<?> getEstudiantePorId(@PathVariable("id") Long id){
       Estudiante estudiante = estudianteService.getEstudiante(id);

       if(estudiante != null){
           return new ResponseEntity<>(estudiante, HttpStatus.OK);
       }else {
           Map<String, String> response = Map.of("message", "Estudiante no encontrado", "status", "404");
           return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
       }
    }

    @PostMapping("")
    public ResponseEntity<?> crearEstudiante(@RequestBody Estudiante estudiante){
       try{
           Estudiante estudianteCreado = estudianteService.guardarEstudiante(estudiante);
           return new ResponseEntity<>(estudianteCreado, HttpStatus.CREATED);
       }catch (DataIntegrityViolationException error){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getRootCause());
       }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEstudiante(@PathVariable("id") Long id, @RequestBody Estudiante estudiante){
        try {
            Optional<Estudiante> estudianteActualizado = estudianteService.actualizarEstudiante(estudiante, id);
            if(estudianteActualizado.isEmpty()){
                Map<String, String> response = Map.of("message", "Estudiante no encontrado", "status", "404");
                return new ResponseEntity(response, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(estudianteActualizado, HttpStatus.OK);
        }catch (DataIntegrityViolationException error){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getRootCause());
        }
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<?> eliminarEstudiante(@PathVariable("id") Long id){
       String estudiante = estudianteService.eliminarEstudiante(id);

         if(estudiante != null){
             Map<String, String> response = Map.of("message", "Estudiante eliminado");
              return new ResponseEntity<>(response, HttpStatus.OK);
         }else {
             Map<String, String> response = Map.of("message", "Estudiante no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
         }

    }
}
