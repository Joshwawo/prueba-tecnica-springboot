package com.example.crudspring.controller;

import com.example.crudspring.entity.Docente;
import com.example.crudspring.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/docentes")
public class DocenteController {
    @Autowired
    private DocenteService docenteService;

    @GetMapping("")
    public List<Docente> getDocentes(){
        return docenteService.getDocentes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Docente> getDocentePorId(@PathVariable("id") Long id){
     Docente docente = docenteService.getDocente(id);
     if(docente != null){
         return new ResponseEntity<>(docente, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }
    }

    @PostMapping("")
    public ResponseEntity<Docente> crearDocente(@RequestBody Docente docente){
        Docente docenteCreado = docenteService.guardarDocente(docente);
        return new ResponseEntity<>(docenteCreado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Docente> actualizarDocente(@PathVariable("id") Long id, @RequestBody Docente docente){
        Docente docenteActualizado = docenteService.actualizarDocente(docente);
        return new ResponseEntity<>(docenteActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminarDocente(@PathVariable("id") Long id){
        Docente docente = docenteService.getDocente(id);
        if(docente != null){
            docenteService.eliminarDocente(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
