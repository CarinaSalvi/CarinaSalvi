package com.exampleCarina.tienda.servicio;

import com.exampleCarina.tienda.entidades.Editorial;
import com.exampleCarina.tienda.errores.ErrorServicio;
import com.exampleCarina.tienda.repositorio.EditorialRepositorio;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialServicio {
    
    @Autowired
    private EditorialRepositorio editRepositorio;
    
    @Transactional
    public Editorial ingresar(Integer id, String nombre) throws ErrorServicio{
        
        validarId(id);
        
        if (nombre==null || nombre.isEmpty() || nombre.contains(" ")) {
            throw new ErrorServicio("El nombre de la EDITORIAL no puede ser nulo.");
        }else{
            Editorial edit = new Editorial();
            edit.setIdEdi(id);
            edit.setNombre(nombre);
            edit.setAlta(Date.from(Instant.now()));
            edit.setBaja(null);
            
            return editRepositorio.save(edit);
        }
    }
    
    @Transactional
    public Editorial modificar (Integer idEdi, String nombre) throws ErrorServicio{
        
        Optional<Editorial> res = editRepositorio.findById(idEdi);
        if (res.isPresent()) {
            Editorial edit = new Editorial();
            edit.setNombre(nombre);
            
            return editRepositorio.save(edit);
        }else{
            throw new ErrorServicio("No se encontró la EDITORIAL solicitada.");
        }
    }
    
    @Transactional
    public Editorial alta (Integer idEdi) throws ErrorServicio{
        
        Optional<Editorial> res = editRepositorio.findById(idEdi);
        if (res.isPresent()) {
            Editorial edit = new Editorial();
            edit.setAlta(Date.from(Instant.now()));
            edit.setBaja(null);
            
            return editRepositorio.save(edit);
        }else{
            throw new ErrorServicio("No se encontró la EDITORIAL solicitada.");
        }
    }
    
    @Transactional
    public Editorial baja (Integer idEdi) throws ErrorServicio{
        
        Optional<Editorial> res = editRepositorio.findById(idEdi);
        if (res.isPresent()) {
            Editorial edit = new Editorial();
            edit.setBaja(Date.from(Instant.now()));
            edit.setAlta(null);
            
            return editRepositorio.save(edit);
        }else{
            throw new ErrorServicio("No se encontró la EDITORIAL solicitada.");
        }
    }
    
    public void validarId (Integer id) throws ErrorServicio{
        
        if (id.equals(null) || id.equals(0)) {
            throw new ErrorServicio("El ID del AUTOR no puede ser nulo.");
        }else{
            String conver = String.valueOf(id);
            boolean a = conver.matches("^[0-9]$"); //Controla que sean todos numéros
            if (a==false) {
                throw new ErrorServicio("El ID debe ser un valor numérico.");
            }    
        }
    }
}
