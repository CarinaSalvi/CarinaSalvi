package com.exampleCarina.tienda.servicio;

import com.exampleCarina.tienda.entidades.Autor;
import com.exampleCarina.tienda.errores.ErrorServicio;
import com.exampleCarina.tienda.repositorio.AutorRepositorio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorServicio {
    
    @Autowired
    private AutorRepositorio autorRepositorio;
    
    @Transactional
    public Autor ingresar(String nombre) throws ErrorServicio{
        
        if (nombre.isEmpty()) {
            throw new ErrorServicio("El nombre del AUTOR no puede ser nulo.");
        }else{
            Autor aut = new Autor();
            aut.setNombre(nombre);
            aut.setAlta(new Date());
            aut.setBaja(null);
            
            return autorRepositorio.save(aut);
        }
    }
    
    @Transactional
    public Autor modificar (Integer idAut, String nombre) throws ErrorServicio{
        
        Optional<Autor> res = autorRepositorio.findById(idAut);
        if (res.isPresent()) {
            Autor aut = res.get();
            aut.setNombre(nombre);
            
            return autorRepositorio.save(aut);
        }else{
            throw new ErrorServicio("No se encontró el AUTOR solicitado.");
        }
    }
    
    @Transactional
    public Autor alta (Integer idAut) throws ErrorServicio{
        
        Optional<Autor> res = autorRepositorio.findById(idAut);
        if (res.isPresent()) {
            Autor aut = res.get();
            aut.setAlta(new Date());
            aut.setBaja(null);
            
            return autorRepositorio.save(aut);
        }else{
            throw new ErrorServicio("No se encontró el AUTOR solicitado.");
        }
    }
    
    @Transactional
    public Autor baja (Integer idAut) throws ErrorServicio{
        
        Optional<Autor> res = autorRepositorio.findById(idAut);
        if (res.isPresent()) {
            Autor aut = res.get();
            aut.setBaja(new Date());
            aut.setAlta(null);
            
            return autorRepositorio.save(aut);
        }else{
            throw new ErrorServicio("No se encontró el AUTOR solicitado.");
        }
    }
    
    //@Transactional
    public List<Autor> listar() throws ErrorServicio{
        
        List<Autor> res = autorRepositorio.findAll();
        if (res.isEmpty()) {
            throw new ErrorServicio("No existen FABRICANTES cargados.");

        }else{
            return res;
        }
    }   
}
