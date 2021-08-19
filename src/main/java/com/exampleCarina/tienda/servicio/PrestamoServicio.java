package com.exampleCarina.tienda.servicio;

import com.exampleCarina.tienda.entidades.Cliente;
import com.exampleCarina.tienda.entidades.Libro;
import com.exampleCarina.tienda.entidades.Prestamo;
import com.exampleCarina.tienda.errores.ErrorServicio;
import com.exampleCarina.tienda.repositorio.PrestamoRepositorio;
import com.exampleCarina.tienda.repositorio.ClienteRepositorio;
import com.exampleCarina.tienda.repositorio.LibroRepositorio;
import java.util.Date;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrestamoServicio {
    
    @Autowired  //El servidor de alpicaciones inicializa SOLO la variable que lo precede
    private PrestamoRepositorio presRepositorio;
    @Autowired  //El servidor de alpicaciones inicializa SOLO la variable que lo precede
    private ClienteRepositorio cliRepositorio;
    @Autowired  //El servidor de alpicaciones inicializa SOLO la variable que lo precede
    private LibroRepositorio libRepositorio;
    
    @Transactional
    public Prestamo crear (Integer idPres, Long idLib, String idCli) throws ErrorServicio {
        
        Optional<Cliente> respCli = cliRepositorio.findById(idCli);
        Optional<Libro> respLib = libRepositorio.findById(idLib);
               
        if (respCli.isPresent()) {
            Cliente cli = respCli.get();
            if (respLib.isPresent()) {
                Libro lib = respLib.get();
                
                Prestamo pres = new Prestamo();
                pres.setAlta(null);
                pres.setBaja(null);
                pres.setFechaPres(new Date());
                Date iniPres = new Date();
                Date finPres = new Date(iniPres.getTime() + (1000 * 60 * 60 * 168));
                pres.setDevolucion(finPres);
                pres.setMulta(50.0);
                
                
                pres.setLibro(lib);
                pres.setCli(cli);
                
                return presRepositorio.save(pres);
            }else{
                throw new ErrorServicio("No se encontró el LIBRO solicitado.");
            }
        }else{
            throw new ErrorServicio("No se encontró el CLIENTE solicitado.");
        }
    }
    
    @Transactional
    public Prestamo modificar (Integer idPres, Double multa) throws ErrorServicio {
        
        Optional<Prestamo> respPres = presRepositorio.findById(idPres);
        if (respPres.isPresent()) {
            Prestamo pres = respPres.get();
            pres.setMulta(multa);
            
            return presRepositorio.save(pres);
        }else{
            throw new ErrorServicio("No se encontró el CLIENTE solicitado.");
        }
    }
    
    @Transactional
    public Prestamo alta (Integer idPres) throws ErrorServicio {
        
        Optional<Prestamo> respPres = presRepositorio.findById(idPres);
        if (respPres.isPresent()) {
            Prestamo pres = respPres.get();
            pres.setAlta(new Date());
            pres.setBaja(null);
            
            return presRepositorio.save(pres);
        }else{
            throw new ErrorServicio("No se encontró el CLIENTE solicitado.");
        }
    }
    
    @Transactional
    public Prestamo baja (Integer idPres) throws ErrorServicio {
        
        Optional<Prestamo> respPres = presRepositorio.findById(idPres);
        if (respPres.isPresent()) {
            Prestamo pres = respPres.get();
            pres.setBaja(new Date());
            pres.setAlta(null);
            
            return presRepositorio.save(pres);
        }else{
            throw new ErrorServicio("No se encontró el CLIENTE solicitado.");
        }
    }
}
