package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.DetalleReservaHabitacionRepo;
import co.edu.uniquindio.proyecto.repositorios.DetalleReservaSillaRepo;
import co.edu.uniquindio.proyecto.repositorios.ReservaRepo;
import co.edu.uniquindio.proyecto.servicios.IReservaServicio;
import co.edu.uniquindio.proyecto.servicios.excepciones.ReservaException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReservaImpl implements IReservaServicio
{
    private final ReservaRepo reservaRepo;
    private final DetalleReservaSillaRepo detalleReservaSillaRepo;
    private final DetalleReservaHabitacionRepo detalleReservaHabitacionRepo;



    public ReservaImpl(ReservaRepo reservaRepo, DetalleReservaSillaRepo detalleReservaSillaRepo, DetalleReservaHabitacionRepo detalleReservaHabitacionRepo) {
        this.reservaRepo = reservaRepo;
        this.detalleReservaSillaRepo = detalleReservaSillaRepo;
        this.detalleReservaHabitacionRepo = detalleReservaHabitacionRepo;

    }

    @Override
    public Reserva reservar(List<Habitacion> habitaciones, List<Silla> sillas, Persona_Usuario usuario, Date fechaLlegada, Date fechaSalida, Date fechaReserva) throws ReservaException {
        //Paso 1, encontrar los hoteles de las habitaciones solicitadas
        ArrayList<Hotel> hoteles = new ArrayList<Hotel>();

        for (int contador = 0; contador < habitaciones.size(); contador++) {
            if (!hoteles.contains(habitaciones.get(contador).getHotel()))
                hoteles.add(habitaciones.get(contador).getHotel());
        }

        //Paso 2, si algún hotel está pausado, no podemos continuar con la reserva

        for (int contador = 0; contador < hoteles.size(); contador++)
        {
            if(true)
            {

            }
        }

        return null;
    }
}
