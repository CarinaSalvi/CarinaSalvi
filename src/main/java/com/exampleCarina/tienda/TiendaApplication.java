package com.exampleCarina.tienda;

import com.exampleCarina.tienda.servicio.ClienteServicio;
import java.time.Instant;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TiendaApplication {

    /*@Autowired
    private ClienteServicio cliServicio;*/
    
    public static void main(String[] args) {
        SpringApplication.run(TiendaApplication.class, args);

    }
    /*@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(cliServicio).passwordEncoder(new BCryptPasswordEncoder());
    }*/
}
