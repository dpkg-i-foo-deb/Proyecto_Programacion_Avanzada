package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.dto.Reserva_Detalles_DTO;
import co.edu.uniquindio.proyecto.dto.Reserva_TotalGastado_DTO;
import co.edu.uniquindio.proyecto.entidades.Persona_Usuario;
import co.edu.uniquindio.proyecto.entidades.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservaRepo extends JpaRepository<Reserva, Integer> {
    @Query("select r.usuario.nombreCompleto, r.fechaReserva, rh.codigoHabitacion from Reserva r, IN(r.listaHabitaciones) rh where rh.codigoHabitacion.hotel.codigoHotel = :idHotel and r.fechaLlegada < :fechaInicio")
    List<Object[]> obtenerReservasHotel(Integer idHotel, Date fechaInicio);

    //@Query("select count(r) from Reserva r join r.listaHabitaciones rh on r = rh.codigoReserva where rh.codigoHabitacion.hotel.codigoHotel = :idHotel and r.fechaLlegada > CURRENT_DATE group by rh.codigoHabitacion.hotel")
    @Query("select count(distinct r) from Reserva r join r.listaHabitaciones rh on r = rh.codigoReserva where rh.codigoHabitacion.hotel.codigoHotel = :idHotel and r.fechaLlegada > CURRENT_DATE")
    int obtenerCantidadReservasPendientes(Integer idHotel);

    @Query("select new co.edu.uniquindio.proyecto.dto.Reserva_TotalGastado_DTO(r.codigo, coalesce((select sum(h.cantidadHabitaciones * h.precio) from r.listaHabitaciones h where h.codigoReserva = r group by r), 0), coalesce((select sum(s.precio) from r.listaSillas s where s.codigoReserva = r group by r), 0)) from Reserva r where r.usuario.cedula = :cedula group by r")
    List<Reserva_TotalGastado_DTO> obtenerTotalGastadoPorReservas(String cedula);

    @Query("select new co.edu.uniquindio.proyecto.dto.Reserva_Detalles_DTO(r, h, s) from Reserva r left join r.listaHabitaciones h on r = h.codigoReserva left join r.listaSillas s on r = s.codigoReserva where r.usuario.cedula = :cedula")
    List<Reserva_Detalles_DTO> obtenerDetallesPorReserva(String cedula);

    List<Reserva> findReservasByUsuario(Persona_Usuario usuario);
}
