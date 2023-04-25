package com.example.crudspring.service;

import com.example.crudspring.entity.Docente;
import com.example.crudspring.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocenteService {
    @Autowired
    private DocenteRepository docenteRepository;

    //Metodo para obtener todos los docentes
    public List<Docente> getDocentes(){
        return docenteRepository.findAll();
    }

    //Metodo para obtener un docente por id
    public Docente getDocente(Long id){
        return docenteRepository.findById(id).orElse(null);
    }

    //Metodo para guardar el docente
    public Docente guardarDocente(Docente docente){
        return docenteRepository.save(docente);
    }

    //Metodo para actualizar el docente
    public Docente actualizarDocente(Docente docente){
        return docenteRepository.save(docente);
    }

    //Metodo para eliminar el docente
    public String eliminarDocente(Long id){
        docenteRepository.deleteById(id);
        return "Docente eliminado correctamente";
    }
}
