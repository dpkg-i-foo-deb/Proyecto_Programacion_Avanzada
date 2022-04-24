package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.entidades.Persona_Usuario;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import co.edu.uniquindio.proyecto.servicios.IUsuarioServicio;
import co.edu.uniquindio.proyecto.servicios.excepciones.UsuarioException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicioImpl implements IUsuarioServicio {
    private final UsuarioRepo usuarioRepo;

    public UsuarioServicioImpl(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public Persona_Usuario registrarUsuario(Persona_Usuario usuario) throws UsuarioException {
        boolean enUso = usuarioRepo.existsByCedulaOrEmail( usuario.getCedula(), usuario.getEmail() );

        if ( enUso ) {
            throw new UsuarioException("La cédula o email ya se encuentran en uso");
        }

        return usuarioRepo.save(usuario);
    }

    @Override
    public Persona_Usuario actualizarUsuario(Persona_Usuario usuario) throws UsuarioException {
        Persona_Usuario buscado = obtenerUsuario(usuario.getCedula());
        if(buscado == null){
            throw new UsuarioException("El usuario no existe");
        }
        return usuarioRepo.save(usuario);
    }

    @Override
    public Persona_Usuario obtenerUsuario(String cedula) throws UsuarioException {
        Optional<Persona_Usuario> usuario = usuarioRepo.findById(cedula);
        if ( usuario.isEmpty() ) {
            throw new UsuarioException("No hay registro que coincida con la cédula especificada");
        }
        return usuario.get();
    }

    @Override
    public void eliminarUsuario(String cedula) throws Exception {
        Persona_Usuario usuario = obtenerUsuario(cedula);
        if(usuario == null){
            throw new UsuarioException("El usuario no existe");
        }
        usuarioRepo.delete(usuario);
    }

    @Override
    public List<Persona_Usuario> listarUsuarios() {
        return usuarioRepo.findAll();
    }

    @Override
    public Persona_Usuario validarLogin(String correo, String password) throws Exception {
        Optional<Persona_Usuario> usuario = usuarioRepo.findByCorreoAndPassword(correo, password);
        if(usuario.isEmpty()){
            throw new UsuarioException("Los datos de autenticación son incorrectos");
        }
        return usuario.get();
    }
}
