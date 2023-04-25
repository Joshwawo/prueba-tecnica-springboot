package com.example.crudspring.service;

import com.example.crudspring.entity.Curso;
import com.example.crudspring.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

     //Metodo para obtener todos los cursos
    public List<Curso> getCursos(){
        return cursoRepository.findAll();
    }

    //Metodo para obtener un curso por id
    public Curso getCurso(Long id){
        return cursoRepository.findById(id).orElse(null);
    }

    //Metodo para guardar el curso
    public Curso guardarCurso(Curso curso){
        return cursoRepository.save(curso);
    }

    //Metodo para actualizar el curso
    public Curso actualizarCurso(Curso curso){
        return cursoRepository.save(curso);
    }

    //Metodo para eliminar el curso
    public String eliminarCurso(Long id){
        cursoRepository.deleteById(id);
        return "Curso eliminado correctamente";
    }
}
