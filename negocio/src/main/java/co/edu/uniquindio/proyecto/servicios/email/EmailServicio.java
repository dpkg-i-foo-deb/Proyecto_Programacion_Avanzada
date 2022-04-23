package co.edu.uniquindio.proyecto.servicios.email;

import co.edu.uniquindio.proyecto.servicios.excepciones.UsuarioException;

import javax.mail.MessagingException;

public interface EmailServicio {
    void recuperarContrasena(String emailUsuario) throws MessagingException, UsuarioException;
}
