package com.exampleCarina.tienda.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Cliente {
    @Id
    private String mail;
    private String contrase;
    private String docu;
    private String nombre;
    private String apellido;
    private String domicilio;
    private String telefono;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date alta;
    @Temporal(TemporalType.TIMESTAMP)
    private Date baja;

    public Cliente() {
    }

    public Cliente(String mail, String contrase, String docu, String nombre, String apellido, String domicilio, String telefono, Date alta, Date baja) {
        this.mail = mail;
        this.contrase = contrase;
        this.docu = docu;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.alta = alta;
        this.baja = baja;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getContrase() {
        return contrase;
    }

    public void setContrase(String contrase) {
        this.contrase = contrase;
    }

    public String getDocu() {
        return docu;
    }

    public void setDocu(String docu) {
        this.docu = docu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getAlta() {
        return alta;
    }

    public void setAlta(Date alta) {
        this.alta = alta;
    }

    public Date getBaja() {
        return baja;
    }

    public void setBaja(Date baja) {
        this.baja = baja;
    }

    
    
    
}
