
package com.exampleCarina.tienda.controlador;

import com.exampleCarina.tienda.entidades.Cliente;
import com.exampleCarina.tienda.errores.ErrorServicio;
import com.exampleCarina.tienda.repositorio.ClienteRepositorio;
import com.exampleCarina.tienda.servicio.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cliente")
public class ClienteControlador {
    
    @Autowired
    private ClienteServicio cliServicio;
    
    @Autowired
    private ClienteRepositorio cliRepositorio;
    
    @GetMapping("/crear")
    public String crearCliente(){
        return "/Cliente/crear-cliente";
    }
    
    @PostMapping("/crear")
    public String crearCliente (ModelMap modelo, @RequestParam String mail, @RequestParam String contrase, @RequestParam String docu, @RequestParam String nombre, 
                                @RequestParam String apellido, @RequestParam String domicilio, @RequestParam String telefono, @RequestParam String contrase1) throws ErrorServicio {
    
        try {
            cliServicio.registrar(mail, contrase, docu, nombre, apellido, domicilio, telefono, contrase1);
            modelo.put("exito", "Registro exitoso");

        }catch (ErrorServicio e) {
            modelo.addAttribute(mail, e).put("error", e.getMessage());
            modelo.put("mail", mail);
            modelo.put("contrase", contrase);
            modelo.put("docu", docu);
            modelo.put("nombre", nombre);
            modelo.put("apellido", apellido);
            modelo.put("domicilio", domicilio);
            modelo.put("telefono", telefono);
            modelo.put("contrase1", contrase1);
            
            return "Cliente/crear-cliente";
        } 
        modelo.put("titulo", "Bienvenido a la librería Arte&Conocimiento");
        modelo.put("descripcion", "Tu usuario fue registrado de manera satisfactoria");
        return "exito";
    }
    
    @GetMapping("/editar-perfil")
    public String Perfil(@RequestParam String mail, ModelMap modelo) throws ErrorServicio { //puede recibir un error (opcional 'required=false')
        try{
            Cliente cli = cliServicio.buscarMail(mail);
            
            modelo.put("mail", cli.getMail());
            modelo.put("docu", cli.getDocu());
            modelo.put("nombre", cli.getNombre());
            modelo.put("apellido", cli.getApellido());
            modelo.put("domicilio", cli.getDomicilio());
            modelo.put("telefono", cli.getTelefono());
            
            return "Cliente/perfil-cliente";
            
        }catch (ErrorServicio e) {
            modelo.put("error", "Ha salido correctamente");
        }
        return "login.html";
    }
  
}
