package com.example.crudspring.service;

import com.example.crudspring.entity.Curso;
import com.example.crudspring.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;
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
}
