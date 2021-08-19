package com.exampleCarina.tienda.servicio;

import com.exampleCarina.tienda.entidades.Cliente;
import com.exampleCarina.tienda.errores.ErrorServicio;
import com.exampleCarina.tienda.repositorio.ClienteRepositorio;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class ClienteServicio  implements UserDetailsService{
    
    @Autowired  //El servidor de alpicaciones inicializa SOLO la variable que lo precede
    private ClienteRepositorio cliRepositorio;
    @Autowired
    private NotificacionesServicio notiServicios;
    
    //El THROWS hace referencia al método de control de errores NUEATRO creado en el paquete ERRORES
    @Transactional
    public void registrar (String mail, String contrase, String docu, String nombre, String apellido, String domicilio, String telefono, String contrase1) throws ErrorServicio{
        
        validarContrase(contrase, contrase1);
        validarDatos(nombre, apellido, domicilio, telefono, mail);
        //validarMail(mail);
        
        int c = validarDni(docu);
        if (c==1) {
            docu = "0" + docu;
        }

        Cliente cli = new Cliente();
        cli.setNombre(nombre);
        cli.setApellido(apellido);
        cli.setMail(mail);
        cli.setDocu(docu);
        cli.setDomicilio(domicilio);
        cli.setTelefono(telefono);
        cli.setAlta(Date.from(Instant.now()));
        cli.setBaja(null);
       
        //USAMOS EL MISMO ENCRIPTADOR QUE USA EL SERVICIO CENTRAL DE SEGURIDAD QUE ESTÁ EN EL MAIN
        //Y AL CLIENTE LO GUARDAMOS CON LA CLAVE ENCRIPTADA
        String encrip = new BCryptPasswordEncoder().encode(contrase);
        cli.setContrase(encrip);

        cliRepositorio.save(cli);
    }
    
    @Transactional
    public void modificar (String mail, String contrase, String docu, String nombre, String apellido, String domicilio, String telefono, String contrase1) throws ErrorServicio{
        
        Optional<Cliente> res = cliRepositorio.findById(mail);
        if (res.isPresent()){
            Cliente cli = res.get();
            validarContrase(contrase, contrase1);
            validarDatos(nombre, apellido, domicilio, telefono, mail);

            int c = validarDni(docu);
            if (c==1) {
                docu = "0" + docu;
            }
            cli.setNombre(nombre);
            cli.setApellido(apellido);
            cli.setMail(mail);
            cli.setDocu(docu);
            cli.setDomicilio(domicilio);
            cli.setTelefono(telefono);
            cli.setAlta(Date.from(Instant.now()));
            cli.setBaja(null);

            //USAMOS EL MISMO ENCRIPTADOR QUE USA EL SERVICIO CENTRAL DE SEGURIDAD QUE ESTÁ EN EL MAIN
            //Y AL CLIENTE LO GUARDAMOS CON LA CLAVE ENCRIPTADA
            String encrip = new BCryptPasswordEncoder().encode(contrase);
            cli.setContrase(encrip);

            cliRepositorio.save(cli);
        }else{
            throw new ErrorServicio("El MAIL ingresado es incorrecto.");
        }
    }
    
    public void baja(String id, Date alta) throws ErrorServicio{
        
        Optional<Cliente> res = cliRepositorio.findById(id);
        if (res.isPresent()) {
            Cliente cli = res.get();
            cli.setBaja(new Date());
            cli.setAlta(null);
            
            cliRepositorio.save(cli);
        }else{
            throw new ErrorServicio("No se encontró el CLIENTE solicitado.");
        }
    }
    
    public Cliente buscarMail(String mail) throws ErrorServicio{
        
        Optional<Cliente> res = cliRepositorio.findById(mail);
        if (res.isPresent()) {
            Cliente cli = res.get();
            return cli;
        }else{
            throw new ErrorServicio("No se encontró el CLIENTE solicitado.");
        }
    }
    
    public void alta(String id, Date alta) throws ErrorServicio{
        
        Optional<Cliente> res = cliRepositorio.findById(id);
        if (res.isPresent()) {
            Cliente cli = res.get();
            cli.setAlta(Date.from(Instant.now()));
            cli.setBaja(null);
            
            cliRepositorio.save(cli);
        }else{
            throw new ErrorServicio("No se encontró el CLIENTE solicitado.");
        }
    }
    
    public void validarContrase(String contrase, String contrase1) throws ErrorServicio{
        
        if (!contrase.equals(contrase1)) {
            throw new ErrorServicio("Las CLAVES deben ser iguales.");
        }
    }
    
    public void validarDatos (String nombre, String apellido, String domicilio, String telefono, String mail) throws ErrorServicio{
        
        if (nombre==null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre del CLIENTE no puede ser nulo.");
        }
        if (apellido==null || apellido.isEmpty()) {
            throw new ErrorServicio("El apellido del CLIENTE no puede ser nulo.");
        }
        if (domicilio==null || domicilio.isEmpty()) {
            throw new ErrorServicio("El domicilio del CLIENTE no puede ser nulo.");
        }
        if (mail==null || mail.isEmpty()) {
            throw new ErrorServicio("El mail del CLIENTE no puede ser nulo.");
        }
        
    }
    
    public int validarDni (String docu) throws ErrorServicio{
        
        if (docu==null || docu.contains(" ") || docu.isEmpty() || docu.length()>8) {
            throw new ErrorServicio("El documento del CLIENTE no puede ser nulo y debe contener 8 dígitos.");
        }
        boolean a = docu.matches("^[0-9]{8}$"); //Controla que sean 8 valores numéricos
        boolean b = docu.matches("^[0-9]{7}$"); //Controla que si son 7 valores numéricos agrega un 0 al inicio.
        int c=0;    //Variable de control para agregar un 0 al inicio del DNI
        
        if (a==false) {
            if (b==true) {
                c=1;
            }else{
                throw new ErrorServicio("El documento debe contener, al menos, 7 dígitos numéricos.");
            }    
        }
        return c;
    }
    
    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        Cliente cli = cliRepositorio.BuscarCorreo(mail);
        if (cli!=null) {
            //LISTA DE PERMISOS PARA LOS CLIENTES
            List<GrantedAuthority> permisos = new ArrayList();
            
            GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_USUARIO_REGISTRADO");
            permisos.add(p1);
            
            // Se guardan los datos del usuario registrado
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuarioSession", cli);
            
//            GrantedAuthority p2 = new SimpleGrantedAuthority("MODULO_LIBRO");
//            permisos.add(p2);
//            GrantedAuthority p3 = new SimpleGrantedAuthority("MODULO_AUTOR");
//            permisos.add(p3);
//            GrantedAuthority p4 = new SimpleGrantedAuthority("MODULO_PRESTAMO");
//            permisos.add(p4);
            //USER ES UNA CLASE DE SPRING SECURITY
            User user = new User(cli.getMail(), cli.getContrase(), permisos);
            return user;
        }else{
            return null;
        }
    }
    
    
}

