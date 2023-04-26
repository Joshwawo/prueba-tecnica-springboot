package com.example.crudspring.controller;

import com.example.crudspring.entity.Nota;
import com.example.crudspring.service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notas")
public class NotaController {

    @Autowired
    private NotaService notaService;

    @GetMapping("/{id}")
    public ResponseEntity<Nota> getNotaPorId (@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        Nota nota = notaService.obtenerNotaPorId(id);
        if(nota != null){
            return ResponseEntity.ok(nota);
        }else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @PostMapping("")
    public ResponseEntity<Nota> crearNota(@RequestBody Nota nota){
        Nota notaCreada = notaService.crearNota(nota);
        if(notaCreada != null){
            return new ResponseEntity<>(notaCreada, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/estudiante")
    public List<Nota> getTodasLasNotas(){
        return notaService.obtenerNotasPorCursoConDetalles();
    }

    @GetMapping("/curso/{id}")
    public List<Nota> getNotasPorCurso(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return notaService.obtenerNotasPorCurso(id);
    }

    @GetMapping("/estudiante/{id}")
    public List<Nota> getNotasPorEstudiante(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return notaService.obtenerNotasPorEstudiante(id);
    }
}
