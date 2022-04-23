package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Persona_Usuario;

import java.util.List;

public interface IUsuarioServicio {
    Persona_Usuario registrarUsuario(Persona_Usuario usuario) throws Exception;

    Persona_Usuario actualizarUsuario(Persona_Usuario usuario) throws Exception;

    Persona_Usuario obtenerUsuario(String codigo);

    void eliminarUsuario(String cedula) throws Exception;

    List<Persona_Usuario> listarUsuarios();

    Persona_Usuario validarLogin(String correo, String password) throws Exception;

}
