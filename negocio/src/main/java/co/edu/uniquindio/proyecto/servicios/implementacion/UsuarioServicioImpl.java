package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.entidades.Persona_Usuario;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import co.edu.uniquindio.proyecto.servicios.IUsuarioServicio;
import co.edu.uniquindio.proyecto.servicios.excepciones.UsuarioException;
import org.springframework.stereotype.Service;

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
}
