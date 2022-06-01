package co.edu.uniquindio.proyecto.bean.usuario;

import co.edu.uniquindio.proyecto.entidades.Hotel;
import co.edu.uniquindio.proyecto.entidades.Persona;
import co.edu.uniquindio.proyecto.servicios.IUsuarioServicio;
import co.edu.uniquindio.proyecto.servicios.excepciones.HotelException;
import co.edu.uniquindio.proyecto.servicios.excepciones.UsuarioException;
import co.edu.uniquindio.proyecto.utils.Mensaje;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.util.List;

@Component
@ViewScoped
public class FavoritosBean {
    @Getter @Setter
    private List<Hotel> hotelesFavoritos;

    @Value(value = "#{seguridadBean.usuario}")
    private Persona usuario;

    private final IUsuarioServicio usuarioServicio;

    public FavoritosBean(IUsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @PostConstruct
    public void init() {
        if(usuario != null)
            hotelesFavoritos = usuarioServicio.obtenerHotelesFavoritos(usuario.getEmail());
    }

    public void agregarFavorito(Hotel hotel) {
        try {
            usuarioServicio.agregarHotelFavorito(hotel, usuario.getEmail());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Hotel Favorito", "Se agregó a favoritos"));
        } catch (UsuarioException | HotelException e) {
            Mensaje.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error Hotel Favorito", e.getMessage());
        }
    }

    public void removerFavorito(Hotel hotel) {
        try {
            usuarioServicio.eliminarHotelFavorito(hotel, usuario.getEmail());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Hotel Favorito", "Se removió de favoritos"));
        } catch (UsuarioException | HotelException e) {
            Mensaje.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error Hotel Favorito", e.getMessage());
        }
    }

    public boolean esFavorito(Hotel hotel) {
        return hotelesFavoritos.contains(hotel);
    }
}
