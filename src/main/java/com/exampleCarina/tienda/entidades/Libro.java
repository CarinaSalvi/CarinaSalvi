package com.exampleCarina.tienda.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Libro {
    @Id
    private Long isbn;
    
    private String titulo;
    private Integer anio;
    private Integer ejemplares;
    private Integer prestados;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date alta;
    @Temporal(TemporalType.TIMESTAMP)
    private Date baja;
    
    @ManyToOne
    private Autor autor;
    @ManyToOne
    private Editorial edit;
    @OneToOne
    private Foto foto;

    public Libro(Long isbn, String titulo, Integer anio, Integer ejemplares, Integer prestados, Date alta, Date baja, Autor autor, Editorial edit) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.anio = anio;
        this.ejemplares = ejemplares;
        this.prestados = prestados;
        this.alta = alta;
        this.baja = baja;
        this.autor = autor;
        this.edit = edit;
    }

    public Libro() {
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(Integer ejemplares) {
        this.ejemplares = ejemplares;
    }

    public Integer getPrestados() {
        return prestados;
    }

    public void setPrestados(Integer prestados) {
        this.prestados = prestados;
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

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editorial getEdit() {
        return edit;
    }

    public void setEdit(Editorial edit) {
        this.edit = edit;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }
    

    @Override
    public String toString() {
        return "Libro{" + "isbn=" + isbn + ", titulo=" + titulo + ", anio=" + anio + ", ejemplares=" + ejemplares + ", prestados=" + prestados + ", alta=" + alta + ", baja=" + baja + ", autor=" + autor + ", edit=" + edit + '}';
    } 
}
