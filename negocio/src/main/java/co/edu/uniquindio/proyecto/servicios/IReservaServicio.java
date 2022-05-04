package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Habitacion;
import co.edu.uniquindio.proyecto.entidades.Persona_Usuario;
import co.edu.uniquindio.proyecto.entidades.Reserva;
import co.edu.uniquindio.proyecto.entidades.Silla;
import co.edu.uniquindio.proyecto.servicios.excepciones.ReservaException;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.List;

public interface IReservaServicio
{
    Reserva reservar(List<Habitacion> habitaciones, List<Silla> sillas, Persona_Usuario usuario, Date fechaLlegada, Date fechaSalida, Date fechaReserva, short cantidadHabitaciones) throws ReservaException;
}
