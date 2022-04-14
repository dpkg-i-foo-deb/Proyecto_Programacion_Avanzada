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
    public void registrarAdministradorHotel(Persona_Administrador_Hotel administradorHotel) throws AdministradorHotelException {
        boolean enUso = administradorHotelRepo.existsByCedulaOrEmail(
                administradorHotel.getCedula(), administradorHotel.getEmail()
        );

        if ( enUso ) {
            throw new AdministradorHotelException("La cédula o email ya se encuentran en uso");
        }

        administradorHotelRepo.save(administradorHotel);
    }

    @Override
    public void eliminarAdministradorHotel(String cedula) throws AdministradorHotelException {
        boolean existe = administradorHotelRepo.existsByCedula(cedula);

        if ( existe ) {
            throw new AdministradorHotelException("No hay registro que coincida con la cédula especificada");
        }

        administradorHotelRepo.deleteById(cedula);
    }

    @Override
    public Persona_Administrador_Hotel actualizarAdministradorHotel(Persona_Administrador_Hotel administradorHotel)
            throws AdministradorHotelException {
        boolean existe = administradorHotelRepo.existsByCedula(administradorHotel.getCedula());

        if ( !existe ) {
            throw new AdministradorHotelException("No hay registro que coincida con la cédula especificada");
        }

        Optional<Persona_Administrador_Hotel> adminRecuperado = administradorHotelRepo
                .findByEmail(administradorHotel.getEmail());

        if ( adminRecuperado.isPresent() && !administradorHotel.equals(adminRecuperado.get()) ) {
            throw new AdministradorHotelException("El email ya fue tomado por otro usuario");
        }

        return administradorHotelRepo.save(administradorHotel);
    }

    @Override
    public Persona_Administrador_Hotel obtenerAdministradorHotel(String cedula) throws AdministradorHotelException {
        Optional<Persona_Administrador_Hotel> adminRecuperado = administradorHotelRepo.findByCedula(cedula);

        if ( adminRecuperado.isEmpty() ) {
            throw new AdministradorHotelException("No hay registro que coincida con la cédula especificada");
        }

        return adminRecuperado.get();
    }

    @Override
    public List<Persona_Administrador_Hotel> listarAdministradoresHotel() {
        return administradorHotelRepo.findAll();
    }
}
