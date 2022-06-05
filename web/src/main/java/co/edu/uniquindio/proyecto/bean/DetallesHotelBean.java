package co.edu.uniquindio.proyecto.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.ICamaServicio;
import co.edu.uniquindio.proyecto.servicios.IComentarioServicio;
import co.edu.uniquindio.proyecto.servicios.IHabitacionServicio;
import co.edu.uniquindio.proyecto.servicios.IHotelServicio;
import co.edu.uniquindio.proyecto.servicios.excepciones.HotelException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

@Component
@ViewScoped
public class DetallesHotelBean implements Serializable {
    @Value("#{param['hotel']}")
    private Integer codigoHotel;

    private final IHotelServicio hotelServicio;

    private final IHabitacionServicio habitacionServicio;

    private final IComentarioServicio comentarioServicio;

    private final ICamaServicio camaServicio;

    @Getter @Setter
    private Hotel hotel;

    @Getter @Setter
    private List<Habitacion> habitaciones;

    @Getter @Setter
    private List<Comentario> comentarios;

    @Getter @Setter
    private List<Caracteristica> caracteristicas;

    @Getter @Setter
    private List<Cama> camas;

    public DetallesHotelBean(IHotelServicio hotelServicio, IHabitacionServicio habitacionServicio,
                             IComentarioServicio comentarioServicio, ICamaServicio camaServicio) {
        this.hotelServicio = hotelServicio;
        this.habitacionServicio = habitacionServicio;
        this.comentarioServicio = comentarioServicio;
        this.camaServicio = camaServicio;
    }

    @PostConstruct
    public void init() {
        try {
            hotel = hotelServicio.obtenerHotel(codigoHotel);

            if(hotel != null) {
                habitaciones = habitacionServicio.listarHabitacionesPorHotel(hotel);
                comentarios = comentarioServicio.obtenerComentariosHotel(hotel);
                caracteristicas = hotelServicio.obtenerCaracteristicasHotel(codigoHotel);
            }
        } catch (HotelException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public String formatearFecha(Date fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");
        return formato.format(fecha);
    }
}
