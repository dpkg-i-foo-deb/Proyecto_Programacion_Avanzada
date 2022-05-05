package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Habitacion;
import co.edu.uniquindio.proyecto.entidades.Persona_Usuario;
import co.edu.uniquindio.proyecto.entidades.Reserva;
import co.edu.uniquindio.proyecto.entidades.Silla;
import co.edu.uniquindio.proyecto.servicios.excepciones.ReservaException;

import java.util.Date;
import java.util.List;

public interface IReservaServicio
{
    Reserva reservar(List<Habitacion> habitaciones, List<Silla> sillas, Persona_Usuario usuario, Date fechaLlegada, Date fechaSalida, short cantidadHabitaciones, Integer cantidadAcompanantes) throws ReservaException;

    Reserva editarReserva(Reserva reserva);

    Reserva eliminarReserva(Reserva reserva);

    List<Reserva> obtenerReservas(Persona_Usuario usuario);
}
