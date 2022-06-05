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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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

            reload();
        } catch (UsuarioException | HotelException e) {
            Mensaje.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error Hotel Favorito", e.getMessage());
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void removerFavorito(Hotel hotel) {
        try {
            usuarioServicio.eliminarHotelFavorito(hotel, usuario.getEmail());

            reload();
        } catch (UsuarioException | HotelException e) {
            Mensaje.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error Hotel Favorito", e.getMessage());
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public boolean esFavorito(Hotel hotel) {
        return hotelesFavoritos.contains(hotel);
    }

    public void favoritoListener(Hotel hotel) {
        if(esFavorito(hotel))
            removerFavorito(hotel);
        else
            agregarFavorito(hotel);
    }

    public void reload() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }
}
