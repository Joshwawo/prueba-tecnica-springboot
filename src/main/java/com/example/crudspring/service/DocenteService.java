package com.example.crudspring.service;

import com.example.crudspring.entity.Docente;
import com.example.crudspring.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public Docente getDocente(String id){
        //limpiar la cadena de entrada y dejar solo los digitos
        String idLimpio = id.replaceAll("[^\\d]", "");
        //convertir la cadena de entrada a un long
        Long longId = Long.parseLong(idLimpio);
        Optional<Docente> docenteBuscado = docenteRepository.findById(longId);
        return docenteBuscado.orElse(null);
    }



    //Metodo para guardar el docente
    public Docente guardarDocente(Docente docente){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String contrasenaEncriptada = passwordEncoder.encode(docente.getContrasena());
        docente.setContrasena(contrasenaEncriptada);
        docente.setContrasenaEncriptada(contrasenaEncriptada);
        docente.setUsuario(docente.getUsuario());
        docente.setCorreo(docente.getCorreo());
        docente.setApellido(docente.getApellido());
        docente.setNombre(docente.getNombre());

        docenteRepository.save(docente);
        System.out.println(docente);
        return docente;
    }

    //Metodo para actualizar el docente
    public Optional<Docente> actualizarDocente(Docente docente, Long id){
        Optional<Docente> docenteBuscado = docenteRepository.findById(id);
        if(docenteBuscado.isPresent()){
            Docente _docente = docenteBuscado.get();
            _docente.setNombre(docente.getNombre());
            _docente.setApellido(docente.getApellido());
            _docente.setCorreo(docente.getCorreo());
            _docente.setUsuario(docente.getUsuario());
            docenteRepository.save(_docente);
        }
        return docenteBuscado;
    }

    //Metodo para eliminar el docente
    public void eliminarDocente(String id){
        //limpiar la cadena de entrada y dejar solo los digitos
        String idLimpio = id.replaceAll("[^\\d]", "");
        //convertir la cadena de entrada a un long
        Long longId = Long.parseLong(idLimpio);

        docenteRepository.deleteById(longId);
    }

    public Optional<Docente> login2(Docente docenteProp){
        Docente docente = docenteRepository.findByUsuario(docenteProp.getUsuario());
        if(docente != null){
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if(passwordEncoder.matches(docenteProp.getContrasena(), docente.getContrasena())){
                System.out.println("Contraseña correcta");
                return Optional.of(docente);
            }
        }
        //Si no se encuentra el docente o la contraseña es incorrecta

        return null;
    }

    public Optional<?> login(String contrasena, String usuario) {
        Docente docente = docenteRepository.findByUsuario(usuario);
        System.out.println(docente);
        //Imprimir el docente
        if(docente != null){
          docente.validarContrasena(contrasena);

            return Optional.of("Contraseña correcta");



        }
        //Si no se encuentra el docente o la contraseña es incorrecta
        System.out.println("Contraseña incorrecta");

        return Optional.empty();

    }
}
