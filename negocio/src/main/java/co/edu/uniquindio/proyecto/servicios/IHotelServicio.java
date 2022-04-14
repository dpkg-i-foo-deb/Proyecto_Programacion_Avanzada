package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Hotel;
import co.edu.uniquindio.proyecto.entidades.Persona_Administrador_Hotel;
import co.edu.uniquindio.proyecto.servicios.excepciones.HotelException;

import java.util.List;

public interface IHotelServicio
{
    Hotel registrarHotel(Hotel hotel, Persona_Administrador_Hotel administrador_hotel) throws HotelException;

    List<Hotel> obtenerHoteles();

    Hotel obtenerHotel(Integer codigo) throws HotelException;

    Hotel editarHotel(Hotel hotel) throws HotelException;

    boolean eliminarHotel(Hotel hotel, Persona_Administrador_Hotel administrador_hotel) throws HotelException;
}
