package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Hotel;
import co.edu.uniquindio.proyecto.servicios.IHotelServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class InicioBean implements Serializable {
    @Getter @Setter
    private List<Hotel> hoteles;

    @Value("#{param['q']}")
    private String hotelBuscado;

    private final IHotelServicio hotelServicio;

    public InicioBean(IHotelServicio hotelServicio) {
        this.hotelServicio = hotelServicio;
    }

    @PostConstruct
    public void init() {
        cargarHoteles();
    }

    public Double obtenerPrecioHabitacionMasEconomica(Integer codigoHotel) {
        return hotelServicio.obtenerPrecioHabitacionMasEconomica(codigoHotel);
    }

    private void cargarHoteles() {


        if(hotelBuscado != null && !hotelBuscado.isEmpty())
            hoteles = hotelServicio.obtenerHotelesPorNombre(hotelBuscado);
        else
            hoteles = hotelServicio.obtenerHoteles();
    }
}
