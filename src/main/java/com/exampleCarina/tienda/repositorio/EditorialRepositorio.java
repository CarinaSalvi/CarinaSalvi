
package com.exampleCarina.tienda.repositorio;

import com.exampleCarina.tienda.entidades.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, Integer> {
    @Query("SELECT e FROM Editorial e WHERE e.idEdi = :ediId")
    public Editorial BuscarIdEditorial (@Param("ediId") Integer ediId);  //Método que devuelve un objeto de tipo Autor que se obtiene por una búsqueda (Query) donde paso el valor buscado por parámetro.

    @Query("SELECT d FROM Editorial d WHERE d.nombre like '%nom%'")
    public Editorial BuscarNom(@Param("nom") String nom);
}
