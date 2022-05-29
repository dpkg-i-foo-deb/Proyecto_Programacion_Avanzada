package co.edu.uniquindio.proyecto.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;

@Component
@ViewScoped
public class BusquedaBean {
    @Getter @Setter
    private String hotelBuscado;

    public String buscarHoteles() {
        String ruta = "index?faces-redirect=true";

        if (hotelBuscado.isEmpty())
            return ruta;

        return ruta + "&amp;q=" + hotelBuscado;
    }
}
