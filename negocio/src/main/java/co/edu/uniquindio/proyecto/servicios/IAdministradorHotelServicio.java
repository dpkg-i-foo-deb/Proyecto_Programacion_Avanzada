package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Persona_Administrador_Hotel;
import co.edu.uniquindio.proyecto.servicios.excepciones.AdministradorHotelException;

import java.util.List;

public interface IAdministradorHotelServicio {
    void registrarAdministradorHotel(Persona_Administrador_Hotel administradorHotel) throws AdministradorHotelException;

    void eliminarAdministradorHotel(String cedula) throws AdministradorHotelException;

    Persona_Administrador_Hotel actualizarAdministradorHotel(Persona_Administrador_Hotel administradorHotel)
            throws AdministradorHotelException;

    Persona_Administrador_Hotel obtenerAdministradorHotel(String cedula) throws AdministradorHotelException;

    List<Persona_Administrador_Hotel> listarAdministradoresHotel();
}
