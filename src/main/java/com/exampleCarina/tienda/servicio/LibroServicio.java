package com.exampleCarina.tienda.servicio;

import com.exampleCarina.tienda.entidades.Autor;
import com.exampleCarina.tienda.entidades.Libro;
import com.exampleCarina.tienda.entidades.Editorial;
import com.exampleCarina.tienda.entidades.Foto;
import com.exampleCarina.tienda.errores.ErrorServicio;
import com.exampleCarina.tienda.repositorio.AutorRepositorio;
import com.exampleCarina.tienda.repositorio.EditorialRepositorio;
import com.exampleCarina.tienda.repositorio.LibroRepositorio;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.String;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class LibroServicio{
    
    @Autowired
    private LibroRepositorio libRepositorio;
    @Autowired
    private AutorRepositorio auRepositorio;
    @Autowired
    private EditorialRepositorio ediRepositorio;
    @Autowired
    private FotoServicio fotoServicio;
        
    @Transactional
    public Libro registrar (MultipartFile archivo, Long isbn, String titulo, Integer anio, Integer ejemplares, Integer prestados, Integer idA, Integer idE) throws ErrorServicio {

        Optional<Libro> respLib = libRepositorio.findById(isbn);
        if (respLib.isPresent()) {
            
            throw new ErrorServicio("El ISBN ingresado ya existe.");
            
        }else{
            
            //Recibe por parámetro los ID de autor y editorial y los busca en la BD a través del REPOSITORIO
            Autor aut = auRepositorio.getOne(idA);  
            //Editorial edit = ediRepositorio.getOne(idE);
            
            validarDatos(isbn, titulo, anio);
            
            Libro lib = new Libro();
            lib.setIsbn(isbn);
            lib.setTitulo(titulo);
            lib.setAnio(anio);
            lib.setEjemplares(ejemplares);
            lib.setPrestados(prestados);
            lib.setAlta(new Date());
            lib.setBaja(null);

            lib.setAutor(aut);
            lib.setEdit(null);

            Foto foto = fotoServicio.guarda(archivo);  //Llama al método guardar foto
            lib.setFoto(foto);  //Asigna la foto al libro

            return libRepositorio.save(lib);
                
        }
    }
    
    @Transactional
    public Libro modificar (MultipartFile archive, Long isbn, String titulo, Integer anio, Integer ejemplares, Integer prestados) throws ErrorServicio {
        
        validarDatos(isbn, titulo, anio);
        
        Optional<Libro> res = libRepositorio.findById(isbn);
        if (res.isPresent()){
            Libro lib = res.get();
            lib.setTitulo(titulo);
            lib.setAnio(anio);
            lib.setEjemplares(ejemplares);
            lib.setPrestados(prestados);

            String idFoto = null; 
            if (lib.getFoto() != null) {
                idFoto = lib.getFoto().getId();
            }
            Foto foto = fotoServicio.actualizar(idFoto, archive);
            lib.setFoto(foto);
            
            return libRepositorio.save(lib);
        }else{
            throw new ErrorServicio("No se encontró el ISBN solicitado.");
        }
    }
    
    @Transactional
    public Libro baja(Long isbn, Date alta) throws ErrorServicio{
        
        Optional<Libro> res = libRepositorio.findById(isbn);
        if (res.isPresent()) {
            Libro lib = res.get();
            lib.setBaja(new Date());
            lib.setAlta(null);
            
            return libRepositorio.save(lib);
        }else{
            throw new ErrorServicio("No se encontró el ISBN solicitado.");
        }
    }
    
    @Transactional
    public Libro alta(Long isbn, Date alta) throws ErrorServicio{
        
        Optional<Libro> res = libRepositorio.findById(isbn);
        if (res.isPresent()) {
            Libro lib = res.get();
            lib.setAlta(new Date());
            lib.setBaja(null);
            
            return libRepositorio.save(lib);
        }else{
            throw new ErrorServicio("No se encontró el ISBN solicitado.");
        }
    }
    
    public void validarDatos (Long isbn, String titulo, Integer anio) throws ErrorServicio{
        
        if (isbn==null || isbn.equals(0)) {
            throw new ErrorServicio("El ISBN del libro no puede ser nulo.");
        }
        
        if (titulo==null || titulo.isEmpty()) {
            throw new ErrorServicio("El titulo del LIBRO no puede ser nulo.");
        }
        
        if (anio==null || anio.equals(0)) {
            throw new ErrorServicio("El AÑO del libro no puede ser nulo.");
        }else{ 
            String x = Integer.toString(anio);
            boolean a = x.matches("{4}"); //("^[1-9]{4}$") Controla que el año sean 4 dígitos
            if (a==false) {
                throw new ErrorServicio("El AÑO deben ser 4 dígitos.");
            }
            
        }
        
    }
    
}
