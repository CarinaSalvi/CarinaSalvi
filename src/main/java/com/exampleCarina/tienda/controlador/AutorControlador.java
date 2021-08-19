
package com.exampleCarina.tienda.controlador;

import com.exampleCarina.tienda.entidades.Autor;
import com.exampleCarina.tienda.errores.ErrorServicio;
import com.exampleCarina.tienda.servicio.AutorServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/autor")
public class AutorControlador {
    
    @Autowired
    private AutorServicio auServicio;
    
    @GetMapping("/crear")
    public String crearAutor(){
        return "/Autor/crear-autor";
        
    }
    
    @PostMapping("/crear")
    public String guardar(ModelMap modelo, @RequestParam String nombre ) throws ErrorServicio {
        
        //Integer idConver = Integer.parseInt(id);
        try {
            auServicio.ingresar(nombre);

            modelo.put("exito", "Registro exitoso");
            return "Autor/crear-autor";
            
        } catch (ErrorServicio e) {
            modelo.put("error", e.getMessage());
            return "Autor/crear-autor";
        }
    }
    
    @GetMapping("/listar")
    public String listarAutor(ModelMap modelo) throws ErrorServicio{
        try{    
            List <Autor> au = auServicio.listar();
            modelo.addAttribute("aut", au);
            return "Autor/listar-autor";
        
        } catch (ErrorServicio e) {
            modelo.put("error", e.getMessage());
            return "Autor/listar-autor";
        }
    }
    
    @GetMapping("/listar1")
    public String listarAutor1(ModelMap modelo) throws ErrorServicio{
        try{    
            List <Autor> au = auServicio.listar();
            modelo.addAttribute("aut", au);
            return "Autor/modificar-autor";
        
        } catch (ErrorServicio e) {
            modelo.put("error", e.getMessage());
            return "Autor/modificar-autor";
        }
        
    }
}
