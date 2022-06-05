package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.entidades.Persona_Administrador;
import co.edu.uniquindio.proyecto.entidades.Persona_Administrador_Hotel;
import co.edu.uniquindio.proyecto.entidades.Vuelo;
import co.edu.uniquindio.proyecto.repositorios.AdministradorHotelRepo;
import co.edu.uniquindio.proyecto.repositorios.AdministradorRepo;
import co.edu.uniquindio.proyecto.repositorios.VueloRepo;
import co.edu.uniquindio.proyecto.servicios.IAdministradorServicio;
import co.edu.uniquindio.proyecto.servicios.excepciones.AdministradorException;
import co.edu.uniquindio.proyecto.servicios.excepciones.AdministradorHotelException;
import co.edu.uniquindio.proyecto.servicios.excepciones.UsuarioException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorServicioImpl implements IAdministradorServicio {

    private final AdministradorRepo administradorRepo;
    private final AdministradorHotelRepo administradorHotelRepo;
    private final VueloRepo vueloRepo;

    public AdministradorServicioImpl(AdministradorRepo administradorRepo, AdministradorHotelRepo administradorHotelRepo, VueloRepo vueloRepo) {
        this.administradorRepo = administradorRepo;
        this.administradorHotelRepo = administradorHotelRepo;
        this.vueloRepo = vueloRepo;
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
    public Persona_Administrador actualizarAdministrador(Persona_Administrador administrador) throws AdministradorException {
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

    @Override
    public Persona_Administrador validarLogin(String correo, String password) throws Exception {
        Optional<Persona_Administrador> administrador = administradorRepo.findByEmailAndContrasena(correo, password);
        if(administrador.isEmpty()){
            throw new UsuarioException("Los datos de autenticación son incorrectos");
        }
        return administrador.get();
    }

    @Override
    public Persona_Administrador_Hotel registrarAdministradorHotel(Persona_Administrador_Hotel administradorHotel) throws AdministradorHotelException {
        boolean enUso = administradorHotelRepo.existsByCedulaOrEmail(administradorHotel.getCedula(), administradorHotel.getEmail());
        if ( enUso ) {
            throw new AdministradorHotelException("La cédula o email ya se encuentran en uso");
        }
        return administradorHotelRepo.save(administradorHotel);
    }

    @Override
    public Vuelo crearVuelo(Vuelo vuelo) {
        return vueloRepo.save(vuelo);
    }

    @Override
    public void eliminarVuelo()
    {

    }

    @Override
    public Boolean esAdmin(String email) {
        return administradorRepo.esAdmin(email);
    }
}

