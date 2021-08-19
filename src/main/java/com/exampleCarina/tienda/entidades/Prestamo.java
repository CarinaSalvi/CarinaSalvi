package com.exampleCarina.tienda.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //(strategy = GenerationType.)
    private Integer idPres;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date alta;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date baja;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPres;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date devolucion;
    
    private Double multa;
    
    @ManyToOne
    private Libro libro;
    @ManyToOne
    private Cliente cli;

    public Prestamo(Integer idPres, Date alta, Date baja, Date fechaPres, Date devolucion, Double multa, Libro libro, Cliente cli) {
        this.idPres = idPres;
        this.alta = alta;
        this.baja = baja;
        this.fechaPres = fechaPres;
        this.devolucion = devolucion;
        this.multa = multa;
        this.libro = libro;
        this.cli = cli;
    }

    public Prestamo() {
    }

    public Integer getIdPres() {
        return idPres;
    }

    public void setIdPres(Integer idPres) {
        this.idPres = idPres;
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

    public Date getFechaPres() {
        return fechaPres;
    }

    public void setFechaPres(Date fechaPres) {
        this.fechaPres = fechaPres;
    }

    public Date getDevolucion() {
        return devolucion;
    }

    public void setDevolucion(Date devolucion) {
        this.devolucion = devolucion;
    }

    public Double getMulta() {
        return multa;
    }

    public void setMulta(Double multa) {
        this.multa = multa;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Cliente getCli() {
        return cli;
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
    }

    @Override
    public String toString() {
        return "Prestamo{" + "idPres=" + idPres + ", alta=" + alta + ", baja=" + baja + ", fechaPres=" + fechaPres + ", devolucion=" + devolucion + ", multa=" + multa + ", libro=" + libro + ", cli=" + cli + '}';
    }

    
}
