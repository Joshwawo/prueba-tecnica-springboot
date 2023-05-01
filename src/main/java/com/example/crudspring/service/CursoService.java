package com.example.crudspring.service;

import com.example.crudspring.entity.Curso;
import com.example.crudspring.entity.Estudiante;
import com.example.crudspring.repository.CursoRepository;
import com.example.crudspring.repository.EstudianteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    public Long limpiarId(String id){
        //limpiar la cadena de entrada y dejar solo los digitos
        String idLimpio = id.replaceAll("[^\\d]", "");
        //convertir la cadena de entrada a un long
        return Long.parseLong(idLimpio);
    }

     //Metodo para obtener todos los cursos
    public List<Curso> getCursos(){
        return cursoRepository.findAll();
    }

    //Metodo para obtener un curso por id
    public Curso getCurso(String id){
        //Limpiaamos el id
        Long longId = limpiarId(id);
        return cursoRepository.findById(longId).orElse(null);
    }

    //Metodo para obtener un curso por nombre
    public Curso getCursoPorNombre(String nombre){
        return cursoRepository.findByNombre(nombre);
    }


    //Metodo para guardar el curso
    public Curso guardarCurso(Curso curso){
        return cursoRepository.save(curso);
    }

    //Metodo para actualizar el curso
    public Optional<Curso> actualizarCurso(Curso curso, Long id){
        Optional<Curso> cursoBuscado = cursoRepository.findById(id);
        if(cursoBuscado.isPresent()){
            Curso _curso = cursoBuscado.get();
            _curso.setNombre(curso.getNombre());
            _curso.setDescripcion(curso.getDescripcion());
            cursoRepository.save(_curso);
        }
        return cursoBuscado;
    }

    //Metodo para eliminar el curso
    public void eliminarCurso(String id){
        //Limpiaamos el id
        Long longId = limpiarId(id);
        cursoRepository.deleteById(longId);
    }

    //Metodo para inscribir estudiantes al curso

    public Map<String, String> inscribirEstudianteAlCurso(Long idEstudiante, Long idCurso) {
        Curso curso = cursoRepository.findById(idCurso).orElseThrow(() -> new RuntimeException("No se encontro el curso"));
        Estudiante estudiante = estudianteRepository.findById(idEstudiante).orElseThrow(() -> new RuntimeException("No se encontro el estudiante"));

       curso.setEstudiantes((List<Estudiante>) estudiante);
       estudiante.setCursos((List<Curso>) curso);

       cursoRepository.save(curso);
       estudianteRepository.save(estudiante);

        return Map.of("mensaje", "Estudiante inscrito correctamente");


    }


    public void agregarCurso(Curso curso) throws IllegalArgumentException {
        if (curso == null) {
            throw new IllegalArgumentException("El curso no puede ser nulo");
        }
        cursoRepository.save(curso);
    }

    public void agregarEstudiante(Estudiante estudiante) throws IllegalArgumentException {
        if (estudiante == null) {
            throw new IllegalArgumentException("El estudiante no puede ser nulo");
        }
        estudianteRepository.save(estudiante);
    }

    //Metodo para desinscribir estudiantes al curso
    public void desinscribirEstuanteAlCurso(Long idEstudiante, Long idCurso){
        Curso curso = cursoRepository.findById(idCurso).orElseThrow(() -> new RuntimeException("No se encontro el curso"));
        Estudiante estudiante = estudianteRepository.findById(idEstudiante).orElseThrow(() -> new RuntimeException("No se encontro el estudiante"));
        curso.getEstudiantes().remove(estudiante);
        estudiante.getCursos().remove(curso);

        cursoRepository.save(curso);
        estudianteRepository.save(estudiante);
    }
}
