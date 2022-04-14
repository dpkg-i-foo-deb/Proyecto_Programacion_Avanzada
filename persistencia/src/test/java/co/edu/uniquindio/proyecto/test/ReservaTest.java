package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReservaTest {
    @Autowired
    private DepartamentoRepo departamentoRepo;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Autowired
    private AdministradorHotelRepo administradorHotelRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private HotelRepo hotelRepo;

    @Autowired
    private ReservaRepo reservaRepo;

    private Departamento depto;
    private Ciudad ciudad;
    private Persona_Administrador_Hotel adminHotel;
    private Persona_Usuario usuario;
    private Hotel hotel;

    private void crearDepartamento() {
        depto = new Departamento("Quindío");
        departamentoRepo.save(depto);
    }

    private void crearCiudad() {
        ciudad = new Ciudad("Armenia", depto);
        ciudadRepo.save(ciudad);
    }

    private void crearAdminHotel() {
        adminHotel = new Persona_Administrador_Hotel(
                "12345",
                "Administrador",
                "admin@email.com",
                "admin123",
                ciudad
        );
        administradorHotelRepo.save(adminHotel);
    }

    private void crearUsuario() {
        usuario = new Persona_Usuario(
                "09876",
                "Usuario",
                "usuario@email.com",
                "usuario123", ciudad
        );
        usuarioRepo.save(usuario);
    }

    private void crearHotel() {
        hotel = new Hotel("Hotelito", "Calle 45-25", ciudad, adminHotel, EstadoHotel.DISPONIBLE);
        hotelRepo.save(hotel);
    }

    @Test
    public void registrarReservaTest() {
        crearDepartamento();
        crearCiudad();
        crearAdminHotel();
        crearUsuario();
        crearHotel();

        Reserva reserva = new Reserva(
                Date.valueOf(LocalDate.now().plusDays(1)),
                Date.valueOf(LocalDate.now().plusDays(30)),
                Date.valueOf(LocalDate.now()),
                EstadoReserva.CONFIRMADA, usuario
        );
        Reserva reservaGuardada = reservaRepo.save(reserva);

        Assertions.assertNotNull(reservaGuardada);
        Assertions.assertEquals(reserva.getCodigo(), reservaGuardada.getCodigo());

        System.out.println(reserva);
    }

    @Test
    public void eliminarReservaTest() {
        crearDepartamento();
        crearCiudad();
        crearAdminHotel();
        crearUsuario();
        crearHotel();

        Reserva reserva = new Reserva(
                Date.valueOf(LocalDate.now().plusDays(1)),
                Date.valueOf(LocalDate.now().plusDays(30)),
                Date.valueOf(LocalDate.now()),
                EstadoReserva.CONFIRMADA, usuario
        );
        reservaRepo.save(reserva);

        int codigo = reserva.getCodigo();

        reservaRepo.delete(reserva);
        Optional<Reserva> reservaRecuperada = reservaRepo.findById(codigo);

        Assertions.assertFalse(reservaRecuperada.isPresent());
    }

    @Test
    public void editarReservaTest() {
        crearDepartamento();
        crearCiudad();
        crearAdminHotel();
        crearUsuario();
        crearHotel();

        Reserva reserva = new Reserva(
                Date.valueOf(LocalDate.now().plusDays(1)),
                Date.valueOf(LocalDate.now().plusDays(30)),
                Date.valueOf(LocalDate.now()),
                EstadoReserva.CONFIRMADA, usuario
        );
        reservaRepo.save(reserva);

        reserva.setEstadoReserva(EstadoReserva.FINALIZADA);

        Reserva reservaModificada = reservaRepo.save(reserva);

        Assertions.assertNotNull(reservaModificada);
        Assertions.assertEquals(EstadoReserva.FINALIZADA, reservaModificada.getEstadoReserva());
    }

    @Test
    public void obtenerReservasTest() {
        crearDepartamento();
        crearCiudad();
        crearAdminHotel();
        crearUsuario();
        crearHotel();

        Reserva reserva1 = new Reserva(
                Date.valueOf(LocalDate.now().plusDays(1)),
                Date.valueOf(LocalDate.now().plusDays(30)),
                Date.valueOf(LocalDate.now()),
                EstadoReserva.CONFIRMADA, usuario
        );
        reservaRepo.save(reserva1);

        Reserva reserva2 = new Reserva(
                Date.valueOf(LocalDate.now().plusMonths(2)),
                Date.valueOf(LocalDate.now().plusMonths(4)),
                Date.valueOf(LocalDate.now().minusMonths(1)),
                EstadoReserva.FINALIZADA, usuario
        );
        reservaRepo.save(reserva2);

        List<Reserva> reservas = reservaRepo.findAll();

        Assertions.assertNotNull(reservas);
        Assertions.assertEquals(2, reservas.size());
    }
}
