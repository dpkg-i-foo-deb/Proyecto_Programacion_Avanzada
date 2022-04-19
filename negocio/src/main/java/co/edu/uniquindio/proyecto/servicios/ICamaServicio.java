package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Cama;
import co.edu.uniquindio.proyecto.entidades.Habitacion;
import co.edu.uniquindio.proyecto.servicios.excepciones.CamaException;

import java.util.List;

public interface ICamaServicio {
    Cama registrarCama(Cama cama);

    Cama actualizarCama(Cama cama) throws CamaException;

    void eliminarCama(Cama cama) throws CamaException;

    Cama obtenerCama(Integer codigo) throws CamaException;

    List<Cama> listarCamasPorHabitacion(Habitacion habitacion);

    List<Cama> listarCamas();
}
