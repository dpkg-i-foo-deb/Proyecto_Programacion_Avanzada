package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.entidades.intermediate.Detalle_Reserva_Habitacion;
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
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) <- Se usaba para trabajar con los DataSet.
public class DetalleReservaHabitacionTest {
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
    private HabitacionRepo habitacionRepo;

    @Autowired
    private ReservaRepo reservaRepo;

    @Autowired
    private DetalleReservaHabitacionRepo detalleReservaHabitacionRepo;

    private Departamento depto;
    private Ciudad ciudad;
    private Persona_Administrador_Hotel adminHotel;
    private Persona_Usuario usuario;
    private Hotel hotel;
    private Habitacion habitacion;
    private Reserva reserva;

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

    private void crearHabitacion() {
        habitacion = new Habitacion(27.00, 5, hotel);
        habitacionRepo.save(habitacion);
    }

    private void crearReserva() {
        reserva = new Reserva(
                Date.valueOf(LocalDate.now().plusDays(1)),
                Date.valueOf(LocalDate.now().plusDays(30)),
                Date.valueOf(LocalDate.now()),
                EstadoReserva.CONFIRMADA, usuario
        );
        reservaRepo.save(reserva);
    }

    @Test
    public void registrarDetalleTest() {
        crearDepartamento();
        crearCiudad();
        crearAdminHotel();
        crearUsuario();
        crearHotel();
        crearHabitacion();
        crearReserva();

        Detalle_Reserva_Habitacion detalle = new Detalle_Reserva_Habitacion(reserva, habitacion, 12.00, (short) 2);
        Detalle_Reserva_Habitacion detalleGuardado = detalleReservaHabitacionRepo.save(detalle);

        Assertions.assertNotNull(detalleGuardado);
        Assertions.assertEquals(detalleGuardado.getCodigo(), detalle.getCodigo());

        System.out.println(detalleGuardado);
        System.out.println(detalle);
    }

    @Test
    public void eliminarDetalleTest() {
        crearDepartamento();
        crearCiudad();
        crearAdminHotel();
        crearUsuario();
        crearHotel();
        crearHabitacion();
        crearReserva();

        Detalle_Reserva_Habitacion detalle = new Detalle_Reserva_Habitacion(reserva, habitacion, 12.00, (short) 2);
        detalle = detalleReservaHabitacionRepo.save(detalle);
        int codigo = detalle.getCodigo();

        detalleReservaHabitacionRepo.delete(detalle);

        Optional<Detalle_Reserva_Habitacion> detalleRecuperado = detalleReservaHabitacionRepo.findById(codigo);

        Assertions.assertFalse(detalleRecuperado.isPresent());
    }

    @Test
    public void editarDetalleTest() {
        crearDepartamento();
        crearCiudad();
        crearAdminHotel();
        crearUsuario();
        crearHotel();
        crearHabitacion();
        crearReserva();

        Detalle_Reserva_Habitacion detalle = new Detalle_Reserva_Habitacion(reserva, habitacion, 12.00, (short) 2);
        detalle = detalleReservaHabitacionRepo.save(detalle);

        detalle.setPrecio(35.22);

        Detalle_Reserva_Habitacion detalleModificado = detalleReservaHabitacionRepo.save(detalle);

        Assertions.assertNotNull(detalleModificado);
        Assertions.assertEquals(detalle.getPrecio(), detalleModificado.getPrecio());
    }

    @Test
    public void obtenerDetalleTest() {
        crearDepartamento();
        crearCiudad();
        crearAdminHotel();
        crearUsuario();
        crearHotel();
        crearHabitacion();
        crearReserva();

        Detalle_Reserva_Habitacion detalle1 = new Detalle_Reserva_Habitacion(reserva, habitacion, 12.00, (short) 2);
        detalleReservaHabitacionRepo.save(detalle1);

        Detalle_Reserva_Habitacion detalle2 = new Detalle_Reserva_Habitacion(reserva, habitacion, 18.00, (short) 4);
        detalleReservaHabitacionRepo.save(detalle2);

        List<Detalle_Reserva_Habitacion> detalles = detalleReservaHabitacionRepo.findAll();

        Assertions.assertEquals(2, detalles.size());
    }

}
