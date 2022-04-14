package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.entidades.Hotel;
import co.edu.uniquindio.proyecto.entidades.Persona_Administrador_Hotel;
import co.edu.uniquindio.proyecto.repositorios.HotelRepo;
import co.edu.uniquindio.proyecto.servicios.IHotelServicio;
import co.edu.uniquindio.proyecto.servicios.excepciones.HotelException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServicioImpl implements IHotelServicio
{
    private final HotelRepo hotelRepo;

    public HotelServicioImpl (HotelRepo hotelRepo)
    {
        this.hotelRepo = hotelRepo;
    }

    @Override
    public Hotel registrarHotel(Hotel hotel, Persona_Administrador_Hotel administrador_hotel) throws HotelException
    {
        if(hotelRepo.existsByDireccion(hotel.getDireccion()))
            throw new HotelException("Ya existe un hotel con la misma dirección");

        administrador_hotel.getListaHoteles().add(hotel);

        hotel.setAdministrador(administrador_hotel);

        return hotelRepo.save(hotel);
    }

    @Override
    public List<Hotel> obtenerHoteles()
    {
        return hotelRepo.findAll();
    }

    @Override
    public Hotel obtenerHotel(Integer codigo) throws HotelException
    {
        Hotel hotel;

        hotel = hotelRepo.findById(codigo).orElse(null);

        if(hotel==null)
            throw new HotelException("El hotel no existe");

        return hotel;
    }
}
