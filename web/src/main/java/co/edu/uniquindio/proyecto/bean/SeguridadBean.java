package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Persona;
import co.edu.uniquindio.proyecto.servicios.IAdministradorHotelServicio;
import co.edu.uniquindio.proyecto.servicios.IAdministradorServicio;
import co.edu.uniquindio.proyecto.servicios.IUsuarioServicio;
import co.edu.uniquindio.proyecto.servicios.excepciones.UsuarioException;
import co.edu.uniquindio.proyecto.utils.Mensaje;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@Component
@Scope("session")
public class SeguridadBean implements Serializable {
    @Getter @Setter
    private Persona usuario;

    @Getter @Setter
    private boolean autenticado;

    @Getter @Setter
    private boolean admin;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String contrasena;

    private final IUsuarioServicio usuarioServicio;
    private final IAdministradorServicio administradorServicio;
    private final IAdministradorHotelServicio adminHotelServicio;

    public SeguridadBean(IUsuarioServicio usuarioServicio, IAdministradorServicio administradorServicio, IAdministradorHotelServicio adminHotelServicio) {
        this.usuarioServicio = usuarioServicio;
        this.administradorServicio = administradorServicio;
        this.adminHotelServicio = adminHotelServicio;
    }

    @PostConstruct
    public void init() {
        usuario = new Persona();
        autenticado = false;
    }

    public String iniciarSesion() {
        try {
            validarLogin();

            Mensaje.mostrarMensaje(FacesMessage.SEVERITY_INFO, "Autenticación Exitosa");
            autenticado = true;

            return "index?faces-redirect=true";
        } catch (Exception e) {
            Mensaje.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error de Autenticación", e.getMessage());
            System.err.println(e.getMessage());
        }

        return "";
    }

    private void validarLogin() throws Exception {
        if(usuarioServicio.esUsuario(email))
            validarUsuario();
        else if(adminHotelServicio.esAdminHotel(email))
            validarAdminHotel();
        else if(administradorServicio.esAdmin(email))
            validarAdmin();
        else
            throw new UsuarioException("Los datos de autenticación son incorrectos");
    }

    private void validarAdmin() throws Exception {
        usuario = administradorServicio.validarLogin(email, contrasena);
        admin = true;
    }

    private void validarAdminHotel() throws Exception {
        usuario = adminHotelServicio.validarLogin(email, contrasena);
        admin = true;
    }

    private void validarUsuario() throws Exception {
        usuario = usuarioServicio.validarLogin(email, contrasena);
    }

    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        autenticado = false;
        admin = false;

        Mensaje.mostrarMensaje(FacesMessage.SEVERITY_INFO, "Sesión terminada");

        return "index?faces-redirect=true";
    }
}
