
package com.exampleCarina.tienda.servicio;

import com.exampleCarina.tienda.entidades.Foto;
import com.exampleCarina.tienda.errores.ErrorServicio;
import com.exampleCarina.tienda.repositorio.FotoRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FotoServicio {
    
    @Autowired
    private FotoRepositorio fotoRepositorio;
    
    public Foto guarda (MultipartFile archivo) throws ErrorServicio{ //MULTIPARTFILE es el archivo donde se almacena la foto
        if (archivo!=null) {
            try{
                Foto foto = new Foto();
                foto.setMime(archivo.getContentType()); //Seteamos el tipo MIME del archivo que viene adjunto
                foto.setNombre(archivo.getName());
                foto.setContenido(archivo.getBytes());

                return fotoRepositorio.save(foto);
                
            }catch (Exception e){
                System.err.println(e.getMessage());
            }
        }
        return null;
    }
    
    public Foto actualizar (String id, MultipartFile archivo) throws ErrorServicio{ //MULTIPARTFILE es el archivo donde se almacena la foto
        if (archivo!=null) {
            try{
                Foto foto = new Foto();
                if (foto!=null) {
                    Optional<Foto> respfoto = fotoRepositorio.findById(id);
                    if (respfoto.isPresent()) {
                        foto = respfoto.get();
                    }
                }
                foto.setMime(archivo.getContentType()); //Seteamos el tipo MIME del archivo que viene adjunto
                foto.setNombre(archivo.getName());
                foto.setContenido(archivo.getBytes());

                return fotoRepositorio.save(foto);
                
            }catch (Exception e){
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

}
