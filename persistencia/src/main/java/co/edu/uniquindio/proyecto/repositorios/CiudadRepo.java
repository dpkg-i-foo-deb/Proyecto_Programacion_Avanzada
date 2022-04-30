package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Hotel;
import co.edu.uniquindio.proyecto.entidades.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CiudadRepo extends JpaRepository<Ciudad, Integer>
{
    @Query("select c.listaHoteles from Ciudad c where c.nombre = :nombreCiudad")
    List<Hotel> obtenerHoteles(String nombreCiudad);
}
