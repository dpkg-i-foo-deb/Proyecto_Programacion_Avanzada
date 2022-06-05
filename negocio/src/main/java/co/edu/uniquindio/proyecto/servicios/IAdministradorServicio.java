package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Persona_Administrador;
import co.edu.uniquindio.proyecto.entidades.Persona_Administrador_Hotel;
import co.edu.uniquindio.proyecto.entidades.Vuelo;
import co.edu.uniquindio.proyecto.servicios.excepciones.AdministradorException;
import co.edu.uniquindio.proyecto.servicios.excepciones.AdministradorHotelException;

import java.util.List;

public interface IAdministradorServicio {

    Persona_Administrador registrarAdministrador(Persona_Administrador administrador) throws AdministradorException;

    void eliminarAdministrador(String cedula) throws AdministradorException;

    Persona_Administrador actualizarAdministrador(Persona_Administrador administrador) throws AdministradorException;

    Persona_Administrador obtenerAdministrador(String cedula) throws AdministradorException;

    List<Persona_Administrador> listarAdministradores();

    Persona_Administrador validarLogin(String correo, String password) throws Exception;

    Persona_Administrador_Hotel registrarAdministradorHotel(Persona_Administrador_Hotel administradorHotel) throws AdministradorHotelException;

    Vuelo crearVuelo(Vuelo vuelo);

    void eliminarVuelo();

    Boolean esAdmin(String email);
}
