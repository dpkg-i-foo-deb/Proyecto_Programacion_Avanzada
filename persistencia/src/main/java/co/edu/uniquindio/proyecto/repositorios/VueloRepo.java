package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.dto.Ciudad_Vuelos_DTO;
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

    @Query("select new co.edu.uniquindio.proyecto.dto.Ciudad_Vuelos_DTO(c.nombre, count(v))  from Ciudad c left join c.vuelosOrigen v on c = v.ciudadOrigen group by c")
    List<Ciudad_Vuelos_DTO> obtenerTotalVuelosPorCiudadOrigen();
}
