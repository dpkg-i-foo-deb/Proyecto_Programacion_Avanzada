package co.edu.uniquindio.proyecto.bean.converter;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.servicios.ICiudadServicio;
import co.edu.uniquindio.proyecto.utils.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;

@Component
public class CiudadConverter implements Converter<Ciudad>, Serializable {
    private final ICiudadServicio ciudadServicio;

    public CiudadConverter(ICiudadServicio ciudadServicio) {
        this.ciudadServicio = ciudadServicio;
    }

    @Override
    public Ciudad getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Ciudad ciudad = null;

        if (s != null && !s.isEmpty()) {
            try {
                ciudad = ciudadServicio.obtenerCiudad(Integer.valueOf(s));
            } catch (Exception e) {
                Mensaje.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", uiComponent.getClientId() + ": id no válido");
                System.err.println("ERROR: " + e.getMessage());
            }
        }

        return ciudad;
    }

    /*
     Representación en texto del objeto.
     */
    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Ciudad ciudad) {
        if (ciudad != null)
            return ciudad.getCodigoCiudad().toString();

        return "";
    }
}
