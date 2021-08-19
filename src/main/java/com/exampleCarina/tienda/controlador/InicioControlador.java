
package com.exampleCarina.tienda.controlador;

import org.springframework.security.access.prepost.PreAuthorize;
import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasAnyRole;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class InicioControlador {
    
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
    @GetMapping("/inicio")
    public String inicio(){
        return "inicio";
    }
    
    @GetMapping("/login")
    public String Login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, ModelMap modelo){ //puede recibir un error (opcional 'required=false')
        if (error!=null) {
            modelo.put("error", "Nombre de usuario o clave incorrecta");
        }
        if (logout!=null) {
            modelo.put("logout", "Ha salido correctamente");
        }
        return "login.html";
    }
    
    
    
}

