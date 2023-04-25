package com.example.crudspring.service;

import com.example.crudspring.entity.Estudiante;
import com.example.crudspring.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService {
    @Autowired

    private EstudianteRepository estudianteRepository;

    //Metodo para obtener todos los estudaintes
    public List<Estudiante> getEstudiantes(){
        return estudianteRepository.findAll();
    }

    //Metodo para obtener un estudiante por id
    public Estudiante getEstudiante(Long id){
        return estudianteRepository.findById(id).orElse(null);
    }

    //Metodo para guardar el estudiante
    public Estudiante guardarEstudiante(Estudiante estudiante){
        return estudianteRepository.save(estudiante);
    }

    //Metodo para actualizar el estudiante
    public Estudiante actualizarEstudiante(Estudiante estudiante){
        return estudianteRepository.save(estudiante);
    }

    //Metodo para eliminar el estudiante
    public String eliminarEstudiante(Long id){
        estudianteRepository.deleteById(id);
        return "Estudiante eliminado correctamente";
    }

}
