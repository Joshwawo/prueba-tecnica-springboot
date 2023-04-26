package com.example.crudspring.controller;

import com.example.crudspring.entity.Docente;
import com.example.crudspring.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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
    public ResponseEntity<?> getDocentePorId(@PathVariable("id") String id){
        try{
            Docente docente = docenteService.getDocente(id);
            if(docente != null){
                return new ResponseEntity<>(docente, HttpStatus.OK);
            }else {
                Map<String, String> erroRes = Map.of("message","No se encontro el docente", "statusCode","404"  );
                return new ResponseEntity<>(erroRes,HttpStatus.NOT_FOUND);
            }

        }catch (DataIntegrityViolationException error){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> crearDocente(@RequestBody Docente docente){
        try{
            Docente docenteCreado = docenteService.guardarDocente(docente);
            return new ResponseEntity<>(docenteCreado, HttpStatus.CREATED);
        }catch (DataIntegrityViolationException error){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @PutMapping("/{id}")
    public  ResponseEntity<?> actualizarDocente(@PathVariable("id") Long id, @RequestBody Docente docente){
       try {
           Optional<Docente> docenteActualizado = docenteService.actualizarDocente(docente, id);
           return new ResponseEntity<>(docenteActualizado, HttpStatus.OK);

       }catch (DataIntegrityViolationException error) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getRootCause());
       }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarDocente(@PathVariable("id") String id){
        Docente docente = docenteService.getDocente(id);
        if(docente != null){
            docenteService.eliminarDocente(id);
            return new ResponseEntity<>(docente,HttpStatus.OK);
        }else {
            Map<String, String> response = Map.of("message", "Docente no encontrado", "status", "404");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginDocente(@RequestBody Docente docente){
        try{
            Optional<Docente> docenteLogueado = (Optional<Docente>) docenteService.login(docente.getContrasenaEncriptada(), docente.getUsuario());
            if(docenteLogueado.isPresent()){
                return new ResponseEntity<>(docenteLogueado, HttpStatus.OK);
            }else {
                Map<String, String> erroRes = Map.of("message","No se encontro el docente", "statusCode","404"  );
                return new ResponseEntity<>(erroRes,HttpStatus.NOT_FOUND);
            }

        }catch (DataIntegrityViolationException error){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
}
