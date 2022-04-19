package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Habitacion;
import co.edu.uniquindio.proyecto.entidades.Hotel;
import co.edu.uniquindio.proyecto.servicios.excepciones.HabitacionException;

import java.util.List;

public interface IHabitacionServicio {
    Habitacion registrarHabitacion(Habitacion habitacion);

    Habitacion actualizarHabitacion(Habitacion habitacion) throws HabitacionException;

    void eliminarHabitacion(Habitacion habitacion) throws HabitacionException;

    Habitacion obtenerHabitacion(Integer codigo) throws HabitacionException;

    List<Habitacion> listarHabitaciones();

    List<Habitacion> listarHabitacionesPorHotel(Hotel hotel);
}
