package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Hotel;
import co.edu.uniquindio.proyecto.entidades.Reserva;
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
    Optional<Hotel> buscarHotelRepetidoPorUbicacion(String direccion, Ciudad ciudad, Integer codigo);

    List<Hotel> findAllByNumeroEstrellas(Short numeroEstrellas);

    Optional<Hotel> findByTelefono(String telefono);

    @Query("select h.ciudad.nombre from Hotel h where h.codigoHotel = :codigo")
    String obtenerNombreCiudad(Integer codigo);
}
