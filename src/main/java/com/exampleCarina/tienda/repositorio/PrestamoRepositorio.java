
package com.exampleCarina.tienda.repositorio;

import com.exampleCarina.tienda.entidades.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepositorio extends JpaRepository<Prestamo, Integer>{
    @Query("SELECT p FROM Prestamo p WHERE p.idPres = :pres")
    public Prestamo BuscarId(@Param("pres") Integer idPres);
    
}
