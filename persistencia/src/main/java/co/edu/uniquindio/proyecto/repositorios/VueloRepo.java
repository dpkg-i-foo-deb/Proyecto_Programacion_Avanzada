package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VueloRepo extends JpaRepository <Vuelo, Integer>
{
    @Query("select v from Vuelo v inner join v.ciudadOrigen c where c.nombre = :nombreCiudad")
    List<Vuelo> obtenerVuelosOrigenCiudad(String nombreCiudad);
}
