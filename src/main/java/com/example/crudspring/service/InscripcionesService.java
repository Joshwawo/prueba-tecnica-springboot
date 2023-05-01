package com.example.crudspring.service;

import com.example.crudspring.entity.Curso;
import com.example.crudspring.entity.Estudiante;
import com.example.crudspring.entity.Inscripciones;
import com.example.crudspring.repository.CursoRepository;
import com.example.crudspring.repository.EstudianteRepository;
import com.example.crudspring.repository.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class InscripcionesService {
    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private CursoRepository cursoRepository;
    public Inscripciones inscribirEstudianteEnCurso(Long idEstudiante, Long idCurso){
        System.out.println("id estudiante: " + idEstudiante);
        System.out.println("id curso: " + idCurso);

        Estudiante estudiante = estudianteRepository.findById(idEstudiante).orElse(null);
        Curso curso = cursoRepository.findById(idCurso).orElse(null);
        Inscripciones inscripcion = new Inscripciones();
        if(estudiante != null && curso != null && inscripcion != null){
            inscripcion.setEstudiante(estudiante);
            inscripcion.setCurso(curso);

            return inscripcionRepository.save(inscripcion);
        }

        return null;
    }

    public void eliminarInscripcion(Long idInscripcion){
        inscripcionRepository.deleteById(idInscripcion);
    }
    public List<Inscripciones> obtenerCursoPorEstudiante(Long idEstudiante){
        Estudiante estudiante = estudianteRepository.findById(idEstudiante).orElse(null);
        return inscripcionRepository.findByEstudiante(estudiante);
    }

    public List<Inscripciones> obtenerEstudiantePorCurso(Long idCurso){
        Curso curso = cursoRepository.findById(idCurso).orElse(null);
        return inscripcionRepository.findByCurso(curso);
    }

    public Optional<Inscripciones> obtenerInscripcionPorId(Long idInscripcion){
        return inscripcionRepository.findById(idInscripcion);
    }

    public Optional<Inscripciones> calificarEstudiante(Long idInscripcion,Inscripciones inscripciones){
        System.out.println("id inscripcion: " + idInscripcion);
        System.out.println("inscripciones: " + inscripciones);
        Inscripciones inscripcion = inscripcionRepository.findById(idInscripcion).orElse(null);
        System.out.println("inscripcion: " + inscripcion);
        if(inscripcion != null){
            inscripcion.setCalifPractica(inscripciones.getCalifPractica());
            inscripcion.setCalifParcial(inscripciones.getCalifParcial());
            inscripcion.setCalifExamen(inscripciones.getCalifExamen());
            inscripcion.setCalifFinal(inscripciones.getCalifFinal());
            return Optional.of(inscripcionRepository.save(inscripcion));

        }

        return null;
    }
}
