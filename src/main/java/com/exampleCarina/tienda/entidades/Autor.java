package com.exampleCarina.tienda.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //(strategy = GenerationType.)
    private Integer idAut;
    private String nombre;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date alta;
    @Temporal(TemporalType.TIMESTAMP)
    private Date baja;

    public Autor(Integer idAut, String nombre, Date alta, Date baja) {
        this.idAut = idAut;
        this.nombre = nombre;
        this.alta = alta;
        this.baja = baja;
    }

    public Autor() {
    }

    public Integer getIdAut() {
        return idAut;
    }

    public void setIdAut(Integer idAut) {
        this.idAut = idAut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    @Override
    public String toString() {
        return "Autor{" + "idAut=" + idAut + ", nombre=" + nombre + ", alta=" + alta + ", baja=" + baja + '}';
    }
}
