
package com.exampleCarina.tienda;

import com.exampleCarina.tienda.servicio.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ConfiguracionLibreria extends WebSecurityConfigurerAdapter {

    @Autowired
    private ClienteServicio  cliServicio;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        /*Exception{
            auth.userDetailsService(cliServicio).passwordEncoder(new BCryptPasswordEncoder());
        }*/
        auth
            .userDetailsService(cliServicio)    //A dónde se busca al cliente
            .passwordEncoder(new BCryptPasswordEncoder());  //Servicio de encriptación (la clave se guarda ya encriptada)

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
            .authorizeRequests()
                .antMatchers("/css/*", "/js/*", "/img/*", "/**")    //Son a las carpetas a las que se tiene acceso sin estar logueado
                .permitAll()
            .and().formLogin()  //Avisa de un FORM para login
                .loginPage("/login")    //Donde está la página URL
                    .loginProcessingUrl("/login-check")  //accion del Formulario (vincula con el controlador)
                    .usernameParameter("email") //Atributo name del HTML
                    .passwordParameter("clave") //Atributo name del HTML
                    .defaultSuccessUrl("/inicio") //Donde se redirecciona si está todo bien (vincula con el controlador)
                    //.failureUrl("/login?error=error")   //Donde se redirecciona si está todo MAL (vincula con el controlador)
                    .permitAll()
                .and().logout()
                    .logoutUrl("/logout")   //Donde se procesa la salida (vincula con el controlador)
                    .logoutSuccessUrl("/")  //Donde se redirecciona al salir
                    .permitAll()
                .and().csrf()
                .disable();
    }
}
