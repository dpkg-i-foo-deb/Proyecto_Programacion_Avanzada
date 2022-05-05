package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Persona_Usuario;

import java.util.List;

public interface IUsuarioServicio {
    Persona_Usuario registrarUsuario(Persona_Usuario usuario) throws Exception;

    Persona_Usuario eliminarUsuario(Persona_Usuario usuario);

    Persona_Usuario editarUsuario (Persona_Usuario usuario);

    List<Persona_Usuario> obtenerUsuarios();
}
