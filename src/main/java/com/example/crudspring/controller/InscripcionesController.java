package com.example.crudspring.controller;

import com.example.crudspring.entity.Inscripciones;
import com.example.crudspring.errorHandler.ErrorHandInscripcion;
import com.example.crudspring.service.InscripcionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/inscripciones")
public class InscripcionesController {
    @Autowired
    private InscripcionesService inscripcionesService;

     @GetMapping("/estudiante/{idEstudiante}")
     public ResponseEntity<?> obtenerCursosPorEstudiante(@PathVariable Long idEstudiante){
         return ResponseEntity.ok(inscripcionesService.obtenerCursoPorEstudiante(idEstudiante));
     }

     @GetMapping("/curso/{idCurso}")
     public ResponseEntity<?> obtenerEstudiantesPorCurso(@PathVariable Long idCurso){
         return ResponseEntity.ok(inscripcionesService.obtenerEstudiantesInscritos(idCurso));
     }

     @GetMapping("/est/{idEstudiante}")
     public ResponseEntity<?> obtenerCursosPorEstudiante2(@PathVariable Long idEstudiante){
         return ResponseEntity.ok(inscripcionesService.obtenerDatosCompletosEstudiante(idEstudiante));
     }

    @PostMapping("/alta")
    public ResponseEntity<?> inscribirEstudianteEnCursos(@RequestBody Inscripciones inscripciones){
         var Idestudiante = inscripciones.getEstudiante().getIdEstudiante();
         var Idcurso = inscripciones.getCurso().getIdCurso();
        try{
            Inscripciones bodyInscripciones = inscripcionesService.inscribirEstudianteEnCurso(Idestudiante, Idcurso);
            return ResponseEntity.ok(bodyInscripciones);

        }catch (ErrorHandInscripcion e){
            return ResponseEntity.badRequest().body(e.getErrorResponseInscripcion());

        }
    }

    @DeleteMapping("/eliminar/{idInscripcion}")
    public ResponseEntity<?> eliminarInscripcion(@PathVariable Long idInscripcion){
        try{
            inscripcionesService.eliminarInscripcion(idInscripcion);
            Map<String, String> response = Map.of("mensaje", "Inscripcion eliminada con exito");
            return ResponseEntity.status(200).body(response);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/calificar/{idInscripcion}")
    public ResponseEntity<?> calificarEstudiante(@PathVariable Long idInscripcion, @RequestBody Inscripciones inscripciones){
        try{
           Optional<Inscripciones> inscripcionesRes = inscripcionesService.calificarEstudiante(idInscripcion, inscripciones);

            return ResponseEntity.status(200).body(inscripcionesRes);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
