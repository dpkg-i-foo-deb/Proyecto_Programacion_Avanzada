package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Departamento;
import co.edu.uniquindio.proyecto.servicios.excepciones.CiudadException;

import java.util.List;

public interface ICiudadServicio
{
    Ciudad registrarCiudad(Ciudad ciudad);

    Ciudad editarCiudad(Ciudad ciudad) throws CiudadException;

    void eliminarCiudad(Ciudad ciudad);

    Ciudad obtenerCiudad(Integer codigo) throws CiudadException;

    List<Ciudad> obtenerCiudades();

    List<Ciudad> obtenerCiudadesDepartamento(Departamento departamento);
}
