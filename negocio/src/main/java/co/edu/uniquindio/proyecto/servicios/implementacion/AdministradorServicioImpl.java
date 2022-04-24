package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.entidades.Persona_Administrador;
import co.edu.uniquindio.proyecto.repositorios.AdministradorRepo;
import co.edu.uniquindio.proyecto.servicios.IAdministradorServicio;
import co.edu.uniquindio.proyecto.servicios.excepciones.AdministradorException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorServicioImpl implements IAdministradorServicio {

    private final AdministradorRepo administradorRepo;

    public AdministradorServicioImpl(AdministradorRepo administradorRepo) {
        this.administradorRepo = administradorRepo;
    }

    @Override
    public Persona_Administrador registrarAdministrador(Persona_Administrador administrador) throws AdministradorException {
        boolean enUso = administradorRepo.existsByCedulaOrEmail(administrador.getCedula(), administrador.getEmail());
        if ( enUso ) {
            throw new AdministradorException("La cédula o email ya se encuentran en uso");
        }
        return administradorRepo.save(administrador);
    }

    @Override
    public void eliminarAdministrador(String cedula) throws AdministradorException {
        boolean existe = administradorRepo.existsByCedula(cedula);
        if ( !existe ) {
            throw new AdministradorException("No hay registro que coincida con la cédula especificada");
        }
        administradorRepo.deleteById(cedula);
    }

    @Override
    public Persona_Administrador actualizarAdministrador(Persona_Administrador administrador)
            throws AdministradorException {
        boolean existe = administradorRepo.existsByCedula(administrador.getCedula());

        if ( !existe ) {
            throw new AdministradorException("El administrador especificado no está registrado");
        }

        return administradorRepo.save(administrador);
    }

    @Override
    public Persona_Administrador obtenerAdministrador(String cedula) throws AdministradorException {
        Optional<Persona_Administrador> administrador = administradorRepo.findById(cedula);
        if ( administrador.isEmpty() ) {
            throw new AdministradorException("No hay registro que coincida con la cédula especificada");
        }
        return administrador.get();
    }

    @Override
    public List<Persona_Administrador> listarAdministradores() {
        return administradorRepo.findAll();
    }
}

