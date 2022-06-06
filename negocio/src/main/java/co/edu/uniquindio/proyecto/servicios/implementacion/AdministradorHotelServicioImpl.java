package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.entidades.EstadoPersona;
import co.edu.uniquindio.proyecto.entidades.Cama;
import co.edu.uniquindio.proyecto.entidades.Habitacion;
import co.edu.uniquindio.proyecto.entidades.Persona_Administrador_Hotel;
import co.edu.uniquindio.proyecto.repositorios.AdministradorHotelRepo;
import co.edu.uniquindio.proyecto.repositorios.CamaRepo;
import co.edu.uniquindio.proyecto.repositorios.HabitacionRepo;
import co.edu.uniquindio.proyecto.servicios.IAdministradorHotelServicio;
import co.edu.uniquindio.proyecto.servicios.excepciones.AdministradorHotelException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorHotelServicioImpl implements IAdministradorHotelServicio {

    private final AdministradorHotelRepo administradorHotelRepo;
    private final CamaRepo camaRepo;
    private final HabitacionRepo habitacionRepo;

    public AdministradorHotelServicioImpl(AdministradorHotelRepo administradorHotelRepo, CamaRepo camaRepo, HabitacionRepo habitacionRepo) {
        this.administradorHotelRepo = administradorHotelRepo;
        this.camaRepo = camaRepo;
        this.habitacionRepo = habitacionRepo;
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
    public Persona_Administrador_Hotel eliminarAdministradorHotel(String cedula) throws AdministradorHotelException {

        //TODO hacer esto mas optimo
        Persona_Administrador_Hotel administrador_hotel = administradorHotelRepo.findByCedula(cedula).orElse(null);

        if ( administrador_hotel == null ) {
            throw new AdministradorHotelException("No hay registro que coincida con la cédula especificada");
        }

        administrador_hotel.setEstadoPersona(EstadoPersona.INACTIVA);

        administrador_hotel=  administradorHotelRepo.save(administrador_hotel);

        return administrador_hotel;
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
        Optional<Persona_Administrador_Hotel> administrador_hotel = administradorHotelRepo.findByEmailAndContrasena(correo, password);
        if(administrador_hotel.isEmpty()){
            throw new AdministradorHotelException("Los datos de autenticación son incorrectos");
        }
        return administrador_hotel.get();
    }

    @Override
    public Cama registrarCama(Cama cama) throws Exception{
        if(cama.getHabitacion().getCodigoHabitacion() == null){
            throw new AdministradorHotelException("Esta habitación no se encuentra registrada");
        }
        return camaRepo.save(cama);
    }

    @Override
    public Habitacion registrarHabitacion(Habitacion habitacion) throws Exception {
        if(habitacion.getHotel().getCodigoHotel() == null){
            throw new AdministradorHotelException("Este hotel no se encuentra registrado");
        }
        return habitacionRepo.save(habitacion);
    }

    @Override
    public Boolean esAdminHotel(String email) {
        return administradorHotelRepo.esAdminHotel(email);
    }
}
