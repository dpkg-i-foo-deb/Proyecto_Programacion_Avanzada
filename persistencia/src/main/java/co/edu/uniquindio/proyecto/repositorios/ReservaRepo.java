package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Habitacion;
import co.edu.uniquindio.proyecto.entidades.Hotel;
import co.edu.uniquindio.proyecto.entidades.Reserva;
import co.edu.uniquindio.proyecto.entidades.intermediate.Detalle_Reserva_Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ReservaRepo extends JpaRepository<Reserva, Integer> {
    @Query("select r.usuario.nombreCompleto, r.fechaReserva, rh.codigoHabitacion from Reserva r, IN(r.listaHabitaciones) rh where rh.codigoHabitacion.hotel.codigoHotel = :idHotel and r.fechaLlegada < :fechaInicio")
    List<Object[]> obtenerReservasHotel(Integer idHotel, Date fechaInicio);

    //@Query("select count(r) from Reserva r join r.listaHabitaciones rh on r = rh.codigoReserva where rh.codigoHabitacion.hotel.codigoHotel = :idHotel and r.fechaLlegada > CURRENT_DATE group by rh.codigoHabitacion.hotel")
    @Query("select count(r) from Reserva r join r.listaHabitaciones rh on r = rh.codigoReserva where rh.codigoHabitacion.hotel.codigoHotel = :idHotel and r.fechaLlegada > CURRENT_DATE")
    int obtenerCantidadReservasPendientes(Integer idHotel);
}
