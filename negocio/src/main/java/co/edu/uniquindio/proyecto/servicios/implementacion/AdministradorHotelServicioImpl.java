package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.entidades.Persona_Administrador_Hotel;
import co.edu.uniquindio.proyecto.repositorios.AdministradorHotelRepo;
import co.edu.uniquindio.proyecto.servicios.IAdministradorHotelServicio;
import co.edu.uniquindio.proyecto.servicios.excepciones.AdministradorHotelException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorHotelServicioImpl implements IAdministradorHotelServicio {

    private final AdministradorHotelRepo administradorHotelRepo;

    public AdministradorHotelServicioImpl(AdministradorHotelRepo administradorHotelRepo) {
        this.administradorHotelRepo = administradorHotelRepo;
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
    public void eliminarAdministradorHotel(String cedula) throws AdministradorHotelException {
        boolean existe = administradorHotelRepo.existsByCedula(cedula);

        if ( !existe ) {
            throw new AdministradorHotelException("No hay registro que coincida con la cédula especificada");
        }

        administradorHotelRepo.deleteById(cedula);
    }

    @Override
    public Persona_Administrador_Hotel actualizarAdministradorHotel(Persona_Administrador_Hotel administradorHotel)
            throws AdministradorHotelException {
        boolean existe = administradorHotelRepo.existsByCedula(administradorHotel.getCedula());

        if ( !existe ) {
            throw new AdministradorHotelException("El administrador especificado no está registrado");
        }

        return administradorHotelRepo.save(administradorHotel);
    }

    @Override
    public Persona_Administrador_Hotel obtenerAdministradorHotel(String cedula) throws AdministradorHotelException {
        Optional<Persona_Administrador_Hotel> administrador_hotel = administradorHotelRepo.findById(cedula);
        if ( administrador_hotel.isEmpty() ) {
            throw new AdministradorHotelException("No hay registro que coincida con la cédula especificada");
        }
        return administrador_hotel.get();
    }

    @Override
    public List<Persona_Administrador_Hotel> listarAdministradoresHotel() {
        return administradorHotelRepo.findAll();
    }

    @Override
    public Persona_Administrador_Hotel validarLogin(String correo, String password) throws Exception {
        return null;
    }
}
