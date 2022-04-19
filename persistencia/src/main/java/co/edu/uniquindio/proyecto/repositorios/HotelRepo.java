package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepo extends JpaRepository <Hotel, Integer>
{
    boolean existsByDireccionAndCiudad(String direccion, Ciudad ciudad);

    List<Hotel> findAllByCiudad(Ciudad ciudad);

    @Query("SELECT h FROM Hotel h WHERE h.direccion = :direccion AND h.ciudad = :ciudad AND h.codigoHotel <> :codigo")
    Hotel findAnotherHotelWithSameAddress(String direccion, Ciudad ciudad, Integer codigo);
}
