
package com.exampleCarina.tienda.controlador;

import com.exampleCarina.tienda.entidades.Autor;
import com.exampleCarina.tienda.errores.ErrorServicio;
import com.exampleCarina.tienda.repositorio.AutorRepositorio;
import com.exampleCarina.tienda.repositorio.LibroRepositorio;
import com.exampleCarina.tienda.servicio.AutorServicio;
import com.exampleCarina.tienda.servicio.LibroServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/libro")
public class LibroControlador {
    
    @Autowired
    private LibroServicio liServicio;
    @Autowired
    private AutorServicio auServicio;

    
    @GetMapping("/crear")
    public String crearCliente(ModelMap modelo) throws ErrorServicio{
        List<Autor> aut = auServicio.listar();
        modelo.addAttribute("autores", aut);
        
        return "/Libro/crear-libro";
    }
    
    @PostMapping("/crear")
    public String crearCliente (ModelMap modelo, MultipartFile archivo, @RequestParam Long isbn, @RequestParam String titulo, @RequestParam Integer anio, @RequestParam Integer prestados, @RequestParam Integer ejemplares, @RequestParam Integer idA) throws ErrorServicio {
        
        try {
            liServicio.registrar(archivo, isbn, titulo, anio, prestados, ejemplares, idA, null);
            modelo.put("exito", "Registro exitoso");

        }catch (ErrorServicio e) {
            modelo.put("error", e.getMessage());
            modelo.put("isbn", isbn);
            modelo.put("titulo", titulo);
            modelo.put("anio", anio);
            modelo.put("ejemplares", ejemplares);
            List<Autor> aut = auServicio.listar();
            modelo.addAttribute("autores", aut);
            modelo.put("archivo", archivo);
            
            return "Libro/crear-libro";
        } 
        modelo.put("titulo", "El libro se creó de manera satisfactoria");
        //modelo.put("descripcion", "Tu usuario fue registrado de manera satisfactoria");
        return "exito";
    }
}
