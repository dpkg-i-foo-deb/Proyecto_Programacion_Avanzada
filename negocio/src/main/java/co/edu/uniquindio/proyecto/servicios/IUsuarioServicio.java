package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Persona_Usuario;
import co.edu.uniquindio.proyecto.servicios.excepciones.UsuarioException;

import javax.mail.MessagingException;

public interface IUsuarioServicio {
    Persona_Usuario registrarUsuario(Persona_Usuario usuario) throws Exception;
}
