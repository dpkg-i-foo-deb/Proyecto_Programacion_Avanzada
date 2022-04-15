package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Hotel;
import co.edu.uniquindio.proyecto.entidades.Persona_Administrador_Hotel;
import co.edu.uniquindio.proyecto.repositorios.HotelRepo;
import co.edu.uniquindio.proyecto.servicios.IHotelServicio;
import co.edu.uniquindio.proyecto.servicios.excepciones.HotelException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Service
public class HotelServicioImpl implements IHotelServicio
{
    private final HotelRepo hotelRepo;

    public HotelServicioImpl (HotelRepo hotelRepo)
    {
        this.hotelRepo = hotelRepo;
    }

    private void existeHotel(Hotel hotel) throws HotelException
    {
        if(hotel==null)
        {
            throw new HotelException("El hotel no existe");
        }

    }

    @Override
    public Hotel registrarHotel(Hotel hotel) throws HotelException {
        boolean existe = hotelRepo.existsByDireccionAndCiudad(hotel.getDireccion(), hotel.getCiudad());

        if ( existe ) {
            throw new HotelException("La dirección ingresada ya fue registrada para otro hotel");
        }

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

    @Override
    public Hotel editarHotel(Hotel hotel) throws HotelException
    {
        Hotel hotelBuscado;

        hotelBuscado = hotelRepo.findById(hotel.getCodigoHotel()).orElse(null);

        existeHotel(hotelBuscado);

        return hotelRepo.save(hotel);
    }

    public boolean eliminarHotel(Hotel hotel) throws HotelException
    {
        Hotel hotelBuscado;

        hotelBuscado=hotelRepo.getById(hotel.getCodigoHotel());

        existeHotel(hotelBuscado);

        hotelRepo.delete(hotel);

        return true;
    }

    public List<Hotel> obtenerHotelesPorCiudad(Ciudad ciudad) {
        return hotelRepo.findAllByCiudad(ciudad);
    }
}