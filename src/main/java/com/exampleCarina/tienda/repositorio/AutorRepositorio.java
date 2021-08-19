package com.exampleCarina.tienda.repositorio;

import com.exampleCarina.tienda.entidades.Autor;
//import javax.swing.Spring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, Integer>{
    @Query("SELECT a FROM Autor a WHERE a.idAut = :autorId")
    public Autor BuscarIdAutor (@Param("autorId") Integer autorId);  //Método que devuelve un objeto de tipo Autor que se obtiene por una búsqueda (Query) donde paso el valor buscado por parámetro.

    @Query("SELECT d FROM Autor d WHERE d.nombre like '%nom'")
    public Autor BuscarNom(@Param("nom") String nom);
}
