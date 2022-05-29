package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
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

    @Query("select h from Hotel h where h.listaComentarios is empty")
    List<Hotel> hotelesSinComentarios();

    @Query("select h from Hotel h where h.nombre like concat('%', trim(both from :patron), '%')")
    List<Hotel> obtenerHotelesPorNombrePatron(String patron);

    @Query("select avg(c.calificacion) from Hotel h, IN(h.listaComentarios) c where h.codigoHotel = :codigoHotel")
    Double obtenerCalificacionPromedio(Integer codigoHotel);

    @Query("select h from Hotel h where h.ciudad.nombre = :nombreCiudad order by h.nombre asc")
    List<Hotel> obtenerHotelesCiudad(String nombreCiudad);

    //@Query("select distinct h from Hotel h, IN(h.habitaciones) hb left join hb.listaReservas dr on hb = dr.codigoHabitacion where (hb.capacidad >= :capacidad) and (hb.precio between :precioInicio and :precioFin) and (:fecha not between dr.codigoReserva.fechaLlegada and dr.codigoReserva.fechaSalida)")
    @Query("select distinct h from Hotel h, IN(h.habitaciones) hb left join hb.listaReservas dr on hb = dr.codigoHabitacion where (hb.capacidad >= :capacidad) and (hb.precio between :precioInicio and :precioFin) and (:fechaInicio > dr.codigoReserva.fechaSalida or :fechaFin < dr.codigoReserva.fechaLlegada)")
    List<Hotel> obtenerHotelesConHabitacionesEnRango(Double precioInicio, Double precioFin, Integer capacidad, Date fechaInicio, Date fechaFin);

    @Query("select min(hb.precio) from Hotel h, IN(h.habitaciones) hb where h.codigoHotel = :codigo")
    Double obtenerPrecioHabitacionMasEconomica(Integer codigo);
}
