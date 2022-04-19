package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Silla;
import co.edu.uniquindio.proyecto.servicios.excepciones.SillaException;

import java.util.List;

public interface ISillaServicio
{
    Silla crearSilla(Silla silla);

    Silla editarSilla(Silla silla) throws SillaException;

    List<Silla> obtenerSillas();

    Silla obtenerSilla(Integer codigo) throws SillaException;

    boolean eliminarSilla(Silla silla);
}
