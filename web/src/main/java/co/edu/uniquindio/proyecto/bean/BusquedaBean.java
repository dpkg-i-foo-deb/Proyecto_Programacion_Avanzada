package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;

@Component
@ViewScoped
public class BusquedaBean {
    @Getter @Setter
    private String hotelBuscado;

    @Getter @Setter
    private Ciudad ciudad;

    public String buscarHoteles() {
        String ruta = "index?faces-redirect=true";

        if (hotelBuscado.isEmpty())
            return ruta;

        return ruta + "&amp;q=" + hotelBuscado;
    }

    public String buscarHotelesCiudad() {
        String ruta = "index?faces-redirect=true";

        if (ciudad == null)
            return ruta;

        return ruta + "&amp;c=" + ciudad.getCodigoCiudad();
    }
}
