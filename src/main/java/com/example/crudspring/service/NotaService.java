package com.example.crudspring.service;

import com.example.crudspring.entity.Curso;
import com.example.crudspring.entity.Estudiante;
import com.example.crudspring.entity.Nota;
import com.example.crudspring.repository.CursoRepository;
import com.example.crudspring.repository.EstudianteRepository;
import com.example.crudspring.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotaService {
    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private  EstudianteRepository estudianteRepository;

    //Metodo para crear una nota
    public Nota crearNota(Nota nota){
        return notaRepository.save(nota);
    }

    public  List<Nota> obtenerNotasPorCursoConDetalles(){
        return notaRepository.findAll();
    }

    //Metodo para obtener nota por id
    public Nota obtenerNotaPorId(Long idNota) throws ChangeSetPersister.NotFoundException {
        return  notaRepository.findById(idNota).orElseThrow(
                ChangeSetPersister.NotFoundException::new
        );

    }

    //Metodo para obtener todas las notas por nombre de estudiante
    public List<Nota> obtenerNotaPorNombreEstudiante(String nombre){
        return  notaRepository.findAllByEstudiante_Nombre(nombre);
    }

    //Metodo para actualizar una nota
    public Nota actualizarNota(Long idNota, Nota notaActualizada) throws ChangeSetPersister.NotFoundException {
        Nota nota = obtenerNotaPorId(idNota);
        nota.setNota(notaActualizada.getNota());
        nota.setCurso(notaActualizada.getCurso());
        nota.setEstudiante(notaActualizada.getEstudiante());
        return notaRepository.save(nota);
    }

    //Metodo para eliminar una nota
    public String eliminarNota(Long idNota) throws ChangeSetPersister.NotFoundException {
        Nota nota = obtenerNotaPorId(idNota);
        notaRepository.delete(nota);
        return "Nota eliminada correctamente";
    }

    //Metodo para obtener todas las notas por curso
    public List<Nota> obtenerNotasPorCurso(Long idCurso) throws ChangeSetPersister.NotFoundException {
        Curso curso = cursoRepository.findById(idCurso)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        return notaRepository.findByCurso(curso);
    }

    //Metodo para obtener todas las notas por estudiante
    public List<Nota> obtenerNotasPorEstudiante(Long idEstudiante) throws ChangeSetPersister.NotFoundException {
        Estudiante estudiante = estudianteRepository.findById(idEstudiante)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        return notaRepository.findByEstudiante(estudiante);
    }

    //Metodo para obtener todas las notas por curso
    /*
    public List<Nota> obtenerNotasPorCurso(Long idCurso) throws ChangeSetPersister.NotFoundException {
        Curso curso = cursoRepository.findById(idCurso)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        return notaRepository.findbyCurso(curso);
    }
    */

    //Metodo para obtener todas las notas por estudiante
    /*
    public List<Nota> obtenerNotasPorEstudiante(Long idEstudiante) throws ChangeSetPersister.NotFoundException {
        Estudiante estudiante = estudianteRepository.findById(idEstudiante)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        return notaRepository.findByEstudiante(estudiante);
    }
    */

}
