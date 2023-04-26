package com.example.crudspring.controller;

import com.example.crudspring.entity.Curso;
import com.example.crudspring.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cursos")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @GetMapping("")
    public List<Curso> getCursos(){
        return cursoService.getCursos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCursoPorId(@PathVariable("id") String id){
      Curso curso = cursoService.getCurso(id);
      if(curso != null){
          return new ResponseEntity<>(curso, HttpStatus.OK);
      }else {
           Map<String, String> response = Map.of("message", "Curso no encontrado", "status", "404");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
      }
    }

    @PostMapping("")
    public ResponseEntity<?> crearCurso(@RequestBody Curso curso){
        try{
            Curso cursoCreado = cursoService.guardarCurso(curso);
            return new ResponseEntity<>(cursoCreado, HttpStatus.CREATED);

        }catch (DataIntegrityViolationException error){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getRootCause());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCurso(@PathVariable("id") Long id, @RequestBody Curso curso){
        Optional<Curso> cursoActualizado = cursoService.actualizarCurso(curso, id);
        return new ResponseEntity<>(cursoActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> eliminarCurso(@PathVariable("id") String id){
        Curso curso = cursoService.getCurso(id);
        if(curso != null){
            cursoService.eliminarCurso(id);
            Map<String, String> response = Map.of("message", "Curso eliminado", "status", "200");
            return new ResponseEntity<>(response,HttpStatus.OK);
        }else {
            Map<String, String> response = Map.of("message", "Curso no encontrado", "status", "404");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }
}
