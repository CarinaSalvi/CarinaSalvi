package com.exampleCarina.tienda.repositorio;

import com.exampleCarina.tienda.entidades.Libro;
import java.util.List;
//import javax.swing.Spring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Long> {
    @Query("SELECT l FROM Libro l WHERE l.isbn LIKE :libIsbn")
    public Libro BuscarIsbn(@Param("libIsbn") Long libIsbn);
    
    @Query("SELECT l FROM Libro l WHERE l.titulo LIKE :libTitulo")
    public List<Libro> BuscarTitulo(@Param("libTitulo") String libTitulo);
    
    @Query("SELECT l FROM Libro l ORDER BY l.titulo")
    public List<Libro> listarDisponibles(@Param("titulo") String titulo);
}
