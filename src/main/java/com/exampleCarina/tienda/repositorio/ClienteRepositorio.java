package com.exampleCarina.tienda.repositorio;

import com.exampleCarina.tienda.entidades.Cliente;
import javax.swing.Spring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, String>{
    @Query("SELECT d FROM Cliente d WHERE d.docu like %:dni%")
    public Cliente BuscarDni(@Param("dni") String dni);
    
    @Query("SELECT d FROM Cliente d WHERE d.apellido like %:ape%")
    public Cliente BuscarApe(@Param("ape") String ape);
    
    @Query("SELECT d FROM Cliente d WHERE d.mail LIKE %:mail%")
    public Cliente BuscarCorreo(@Param("mail") String mail);

    //public Cliente findById(String id);

  }
