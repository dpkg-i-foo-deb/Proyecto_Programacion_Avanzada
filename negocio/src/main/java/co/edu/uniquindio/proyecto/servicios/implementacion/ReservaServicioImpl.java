package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.entidades.intermediate.Detalle_Reserva_Habitacion;
import co.edu.uniquindio.proyecto.entidades.intermediate.Detalle_Reserva_Silla;
import co.edu.uniquindio.proyecto.repositorios.DetalleReservaHabitacionRepo;
import co.edu.uniquindio.proyecto.repositorios.DetalleReservaSillaRepo;
import co.edu.uniquindio.proyecto.repositorios.ReservaRepo;
import co.edu.uniquindio.proyecto.servicios.IReservaServicio;
import co.edu.uniquindio.proyecto.servicios.excepciones.ReservaException;
import org.springframework.stereotype.Service;
import java.sql.Date;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservaServicioImpl implements IReservaServicio
{
    private final ReservaRepo reservaRepo;
    private final DetalleReservaSillaRepo detalleReservaSillaRepo;
    private final DetalleReservaHabitacionRepo detalleReservaHabitacionRepo;



    public ReservaServicioImpl(ReservaRepo reservaRepo, DetalleReservaSillaRepo detalleReservaSillaRepo, DetalleReservaHabitacionRepo detalleReservaHabitacionRepo) {
        this.reservaRepo = reservaRepo;
        this.detalleReservaSillaRepo = detalleReservaSillaRepo;
        this.detalleReservaHabitacionRepo = detalleReservaHabitacionRepo;

    }

    @Override
    public Reserva reservar(List<Habitacion> habitaciones, List<Silla> sillas, Persona_Usuario usuario, java.util.Date fechaLlegada, java.util.Date fechaSalida, short cantidadHabitaciones) throws ReservaException {
        //Paso 1, encontrar los hoteles de las habitaciones solicitadas
        ArrayList<Hotel> hoteles = new ArrayList<>();
        Reserva reserva = new Reserva();
        Detalle_Reserva_Habitacion detalle_reserva_habitacion = new Detalle_Reserva_Habitacion();
        ArrayList<Detalle_Reserva_Habitacion> detalles_habitacion = new ArrayList<>();
        ArrayList<Detalle_Reserva_Silla> detalles_sillas = new ArrayList<>();
        Detalle_Reserva_Silla detalle_silla = new Detalle_Reserva_Silla();

        for (Habitacion habitacionesEntrada : habitaciones) {
            if (!hoteles.contains(habitacionesEntrada.getHotel()))
                hoteles.add(habitacionesEntrada.getHotel());
        }

        //Paso 2, si algún hotel está pausado, no podemos continuar con la reserva

        for (Hotel hotelesFor : hoteles) {
            if (hotelesFor.getEstadoHotel().equals(EstadoHotel.PAUSADO)) {
                throw new ReservaException("El hotel de la habitación solicitada está pausada");
            }
        }



        //Paso 3, crear y guardar la reserva inicial para tener el código
        reserva.setUsuario(usuario);
        reserva.setFechaLlegada(fechaLlegada);
        reserva.setFechaSalida(fechaSalida);
        reserva.setFechaReserva(Date.valueOf(LocalDate.now()));
        reserva.setEstadoReserva(EstadoReserva.CONFIRMADA);
        reserva.setListaSillas(detalles_sillas);
        reserva.setListaHabitaciones(detalles_habitacion);


        reserva = reservaRepo.save(reserva);

        //Paso 4, en caso de que se deseen reservar sillas, agregarlas al detalle
        if(!sillas.isEmpty())
        {
            for (Silla silla : sillas) {
                detalle_silla = new Detalle_Reserva_Silla();

                detalle_silla.setCodigoReserva(reserva);
                detalle_silla.setPrecio(silla.getPrecio());

                detalles_sillas.add(detalle_silla);
            }
        }

        //Paso 5, agregar las habitaciones deseadas a los detalles de habitación
        for (Habitacion habitacionesFor : habitaciones) {
            detalle_reserva_habitacion = new Detalle_Reserva_Habitacion();

            detalle_reserva_habitacion.setCodigoHabitacion(habitacionesFor);
            detalle_reserva_habitacion.setCodigoReserva(reserva);
            detalle_reserva_habitacion.setPrecio(habitacionesFor.getPrecio());
            detalle_reserva_habitacion.setCantidadHabitaciones(cantidadHabitaciones);

            detalles_habitacion.add(detalle_reserva_habitacion);
        }

        reserva.setListaHabitaciones(detalles_habitacion);



        return reserva;
    }
}
