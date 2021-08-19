package com.exampleCarina.tienda.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificacionesServicio {
    
    @Autowired
    //INCORPORRAMOS UNA LIBRERIA CON MÉTODOS PROPIOS PARA NOTIFICACIONES POR MAIL
    private JavaMailSender mailSender;
   
    @Async  //EL HILO DE EJECUCIÓN NO ESPERA A TERMINAR CON ESE MÉTODO SINO QUE SE EJECUTA AL MISMO TIEMPO QUE OTROS
    public void enviar(String cuerpo, String titulo, String destinatario){
        //SimpleMailMessage ES UNA CLASE DE LA LIBRERÍA QUE IMPORTAMOS y mensaje ES UN OBJETO DE ELLA
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(destinatario);
        mensaje.setFrom("carina_salvi@yahoo.com.ar");
        mensaje.setText(cuerpo);
        mensaje.setSubject(titulo);
        
        mailSender.send(mensaje);
        
    }
}
