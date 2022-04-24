package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Persona_Administrador;
import co.edu.uniquindio.proyecto.servicios.excepciones.AdministradorException;

import java.util.List;

public interface IAdministradorServicio {

    Persona_Administrador registrarAdministrador(Persona_Administrador administrador) throws AdministradorException;

    void eliminarAdministrador(String cedula) throws AdministradorException;

    Persona_Administrador actualizarAdministrador(Persona_Administrador administrador) throws AdministradorException;

    Persona_Administrador obtenerAdministrador(String cedula) throws AdministradorException;

    List<Persona_Administrador> listarAdministradores();
}
