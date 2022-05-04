package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Vuelo;
import co.edu.uniquindio.proyecto.servicios.excepciones.VueloException;

import java.util.List;

public interface IVueloServicio
{
    Vuelo crearVuelo(Vuelo vuelo) throws VueloException;

    List<Vuelo> obtenerVuelos();

    Vuelo obtenerVuelo(Integer codigo) throws VueloException;

    Vuelo editarVuelo(Vuelo vuelo) throws VueloException;

    boolean eliminarVuelo(Vuelo vuelo);
}
