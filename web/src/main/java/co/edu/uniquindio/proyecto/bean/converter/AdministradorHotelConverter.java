package co.edu.uniquindio.proyecto.bean.converter;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Persona_Administrador_Hotel;
import co.edu.uniquindio.proyecto.servicios.IAdministradorHotelServicio;
import co.edu.uniquindio.proyecto.servicios.ICiudadServicio;
import co.edu.uniquindio.proyecto.utils.Mensaje;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;

@Component
public class AdministradorHotelConverter implements Converter<Persona_Administrador_Hotel>, Serializable {
    private final IAdministradorHotelServicio administradorHotelServicio;

    public AdministradorHotelConverter(IAdministradorHotelServicio administradorHotelServicio) {
        this.administradorHotelServicio = administradorHotelServicio;
    }

    @Override
    public Persona_Administrador_Hotel getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Persona_Administrador_Hotel admin = null;

        if (s != null && !s.isEmpty()) {
            try {
                admin = administradorHotelServicio.obtenerAdministradorHotel(s);
            } catch (Exception e) {
                Mensaje.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", uiComponent.getClientId() + ": id no válido");
                System.err.println("ERROR: " + e.getMessage());
            }
        }

        return admin;
    }

    /*
     Representación en texto del objeto.
     */
    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Persona_Administrador_Hotel admin) {
        if (admin != null)
            return admin.getCedula();

        return "";
    }
}
