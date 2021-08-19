package com.exampleCarina.tienda.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Editorial {
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //(strategy = GenerationType.)
    private Integer idEdi;
    private String nombre;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date alta;
    @Temporal(TemporalType.TIMESTAMP)
    private Date baja;
    
//    @OneToMany
//    private List<Libro> lib;
//    private Object GeneratinType;

    public Editorial(Integer idEdi, String nombre, Date alta, Date baja) {
        this.idEdi = idEdi;
        this.nombre = nombre;
        this.alta = alta;
        this.baja = baja;
    }

    public Editorial() {
    }

    public Integer getIdEdi() {
        return idEdi;
    }

    public void setIdEdi(Integer idEdi) {
        this.idEdi = idEdi;
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
        return "Editorial{" + "idEdi=" + idEdi + ", nombre=" + nombre + ", alta=" + alta + ", baja=" + baja + '}';
    }
}
