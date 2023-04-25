package com.example.crudspring.controller;

import com.example.crudspring.entity.Curso;
import com.example.crudspring.service.CursoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Curso> getCursoPorId(@PathVariable("id") Long id){
      Curso curso = cursoService.getCurso(id);
      if(curso != null){
          return new ResponseEntity<>(curso, HttpStatus.OK);
      }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @PostMapping("")
    public ResponseEntity<Curso> crearCurso(@RequestBody Curso curso){
        Curso cursoCreado = cursoService.guardarCurso(curso);
        return new ResponseEntity<>(cursoCreado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizarCurso(@PathVariable("id") Long id, @RequestBody Curso curso){
        Curso cursoActualizado = cursoService.actualizarCurso(curso);
        return new ResponseEntity<>(cursoActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminarCurso(@PathVariable("id") Long id){
        Curso curso = cursoService.getCurso(id);
        if(curso != null){
            cursoService.eliminarCurso(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
