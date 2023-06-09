package com.example.crudspring.service;

import com.example.crudspring.entity.Estudiante;
import com.example.crudspring.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    //Metodo para obtener un estudiate por nombre
    public Estudiante getEstudianteByNombre(String nombre){
        return estudianteRepository.findByNombre(nombre);
    }

    //Metodo para guardar el estudiante
    public Estudiante guardarEstudiante(Estudiante estudiante){
        return estudianteRepository.save(estudiante);
    }

    //Metodo para actualizar el estudiante
    public Optional<Estudiante> actualizarEstudiante(Estudiante estudiante, Long id){
        Optional<Estudiante> estudianteBuscado = estudianteRepository.findById(id);

        if(estudianteBuscado.isPresent()){
            Estudiante _estudiante = estudianteBuscado.get();
            _estudiante.setNombre(estudiante.getNombre());
            _estudiante.setApellido(estudiante.getApellido());
            _estudiante.setCorreo(estudiante.getCorreo());
            _estudiante.setUsuario(estudiante.getUsuario());
            estudianteRepository.save(_estudiante);
        }
        return estudianteBuscado;


    }

    //Metodo para eliminar el estudiante
    public String eliminarEstudiante(Long id){
      Optional<Estudiante> estudianteBuscado = estudianteRepository.findById(id);
        if (estudianteBuscado.isPresent()){
            estudianteRepository.deleteById(id);
            return "Estudiante eliminado correctamente";
        }else{
            return null;
        }

    }

}
