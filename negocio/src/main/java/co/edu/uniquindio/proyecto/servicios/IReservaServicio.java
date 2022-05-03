package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Reserva;
import co.edu.uniquindio.proyecto.servicios.excepciones.ReservaException;

public interface IReservaServicio
{
    Reserva reservar() throws ReservaException;
}
