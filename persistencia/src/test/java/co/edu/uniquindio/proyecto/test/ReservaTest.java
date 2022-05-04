package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.Reserva_Detalles_DTO;
import co.edu.uniquindio.proyecto.dto.Reserva_TotalGastado_DTO;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.entidades.intermediate.Detalle_Reserva_Habitacion;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

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

    @Autowired
    private HabitacionRepo habitacionRepo;

    @Autowired
    private DetalleReservaHabitacionRepo detalleReservaHabitacionRepo;

    private Departamento depto;
    private Ciudad ciudad;
    private Persona_Administrador_Hotel adminHotel;
    private Persona_Usuario usuario;
    private Hotel hotel;
    private Habitacion habitacion;

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
        hotel = new Hotel("Hotelito", (short) 5, "Calle 45-25", ciudad, adminHotel, EstadoHotel.DISPONIBLE);
        hotelRepo.save(hotel);
    }

    private void crearHabitacion() {
        habitacion = new Habitacion(27.00, 5, hotel);
        hotel.getHabitaciones().add(habitacion);
        habitacionRepo.save(habitacion);
    }

    @Test
    public void registrarReservaTest() {
        crearDepartamento();
        crearCiudad();
        crearAdminHotel();
        crearUsuario();
        crearHotel();
        crearHabitacion();

        Detalle_Reserva_Habitacion detalle_reserva_habitacion= new Detalle_Reserva_Habitacion();

        Reserva reserva = new Reserva(
                Date.valueOf(LocalDate.now().plusDays(1)),
                Date.valueOf(LocalDate.now().plusDays(30)),
                Date.valueOf(LocalDate.now()),
                EstadoReserva.CONFIRMADA,
                usuario
        );

        Reserva reservaGuardada = reservaRepo.save(reserva);


        Assertions.assertNotNull(reservaGuardada);
        Assertions.assertEquals(reserva.getCodigo(), reservaGuardada.getCodigo());

        detalle_reserva_habitacion.setCodigoReserva(reserva);

        detalle_reserva_habitacion.setCodigoHabitacion(habitacion);

        detalle_reserva_habitacion.setCantidadHabitaciones((short) 1);

        detalle_reserva_habitacion.setPrecio(habitacion.getPrecio());

       detalle_reserva_habitacion= detalleReservaHabitacionRepo.save(detalle_reserva_habitacion);

       Assertions.assertNotNull(detalle_reserva_habitacion);

        System.out.println(reserva);
    }

    @Test
    public void eliminarReservaTest() {
        crearDepartamento();
        crearCiudad();
        crearAdminHotel();
        crearUsuario();
        crearHotel();
        crearHabitacion();

        Detalle_Reserva_Habitacion detalle_reserva_habitacion= new Detalle_Reserva_Habitacion();

        Reserva reserva = new Reserva(
                Date.valueOf(LocalDate.now().plusDays(1)),
                Date.valueOf(LocalDate.now().plusDays(30)),
                Date.valueOf(LocalDate.now()),
                EstadoReserva.CONFIRMADA, usuario
        );
        reservaRepo.save(reserva);


        detalle_reserva_habitacion.setCodigoReserva(reserva);

        detalle_reserva_habitacion.setCodigoHabitacion(habitacion);

        detalle_reserva_habitacion.setCantidadHabitaciones((short) 1);

        detalle_reserva_habitacion.setPrecio(habitacion.getPrecio());

        detalle_reserva_habitacion= detalleReservaHabitacionRepo.save(detalle_reserva_habitacion);

        int codigo = reserva.getCodigo();
        int codigoDetalle = detalle_reserva_habitacion.getCodigo();

        reservaRepo.delete(reserva);
        detalleReservaHabitacionRepo.delete(detalle_reserva_habitacion);

        Optional<Reserva> reservaRecuperada = reservaRepo.findById(codigo);
        Optional<Detalle_Reserva_Habitacion> detalleRecuperado = detalleReservaHabitacionRepo.findById(codigoDetalle);


        Assertions.assertFalse(reservaRecuperada.isPresent());
        Assertions.assertFalse(detalleRecuperado.isPresent());
    }

    @Test
    public void editarReservaTest() {
        crearDepartamento();
        crearCiudad();
        crearAdminHotel();
        crearUsuario();
        crearHotel();
        crearHabitacion();

        Detalle_Reserva_Habitacion detalle_reserva_habitacion= new Detalle_Reserva_Habitacion();

        Reserva reserva = new Reserva(
                Date.valueOf(LocalDate.now().plusDays(1)),
                Date.valueOf(LocalDate.now().plusDays(30)),
                Date.valueOf(LocalDate.now()),
                EstadoReserva.CONFIRMADA, usuario
        );
        reservaRepo.save(reserva);

        detalle_reserva_habitacion.setCodigoReserva(reserva);

        detalle_reserva_habitacion.setCodigoHabitacion(habitacion);

        detalle_reserva_habitacion.setCantidadHabitaciones((short) 1);

        detalle_reserva_habitacion.setPrecio(habitacion.getPrecio());

        detalle_reserva_habitacion= detalleReservaHabitacionRepo.save(detalle_reserva_habitacion);

        detalle_reserva_habitacion.setPrecio(1000000.00);

        reserva.setEstadoReserva(EstadoReserva.FINALIZADA);

        Reserva reservaModificada = reservaRepo.save(reserva);
        Detalle_Reserva_Habitacion detalleModificado = detalleReservaHabitacionRepo.save(detalle_reserva_habitacion);


        Assertions.assertNotNull(reservaModificada);
        Assertions.assertNotNull(detalleModificado);
        Assertions.assertEquals(EstadoReserva.FINALIZADA, reservaModificada.getEstadoReserva());
        Assertions.assertEquals(1000000.00, detalleModificado.getPrecio());
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

    @Test
    public void listarReservasHotel() {
        crearDepartamento();
        crearCiudad();
        crearAdminHotel();
        crearUsuario();
        crearHotel();
        crearHabitacion();

        Reserva reserva1 = new Reserva(
                Date.valueOf(LocalDate.of(2023, 1, 10)),
                Date.valueOf(LocalDate.of(2023, 5, 1)),
                Date.valueOf(LocalDate.of(2022, 2, 15)),
                EstadoReserva.CONFIRMADA, usuario
        );
        reservaRepo.save(reserva1);

        Reserva reserva2 = new Reserva(
                Date.valueOf(LocalDate.of(2022, 11, 3)),
                Date.valueOf(LocalDate.of(2023, 1, 1)),
                Date.valueOf(LocalDate.of(2022, 4, 30)),
                EstadoReserva.FINALIZADA, usuario
        );
        reservaRepo.save(reserva2);

        Detalle_Reserva_Habitacion detalle = new Detalle_Reserva_Habitacion(reserva1, habitacion, 12.00, (short) 2);
        detalleReservaHabitacionRepo.save(detalle);

        detalle = new Detalle_Reserva_Habitacion(reserva2, habitacion, 12.00, (short) 2);
        detalleReservaHabitacionRepo.save(detalle);

        List<Object[]> reservas = reservaRepo.obtenerReservasHotel(hotel.getCodigoHotel(), Date.valueOf(LocalDate.of(2023, 1, 1)));

        reservas.forEach(r -> System.out.println(r[0] + " - " + r[1] + " - " + r[2]));

        Assertions.assertNotNull(reservas);
        Assertions.assertEquals(1, reservas.size());
    }

    @Test
    @Sql("classpath:DatosSQL.sql")
    public void totalReservasPendientesHotel() {
        int reservas = reservaRepo.obtenerCantidadReservasPendientes(1);

        System.out.println("TOTAL: " + reservas);
        Assertions.assertEquals(2, reservas);
    }

    @Test
    @Sql("classpath:DatosSQL.sql")
    public void obtenerTotalGastadoPorReservas() {
        List<Reserva_TotalGastado_DTO> res = reservaRepo.obtenerTotalGastadoPorReservas("55555");

        res.forEach(System.out::println);
        Assertions.assertEquals(3, res.size());
        Assertions.assertEquals(360_000, res.get(0).getTotalGastadoReservas());
        Assertions.assertEquals(43_000, res.get(0).getTotalGastadoSillas());
        Assertions.assertEquals(304_000, res.get(1).getTotalGastadoReservas());
        Assertions.assertEquals(0, res.get(1).getTotalGastadoSillas());
        Assertions.assertEquals(0, res.get(2).getTotalGastadoReservas());
        Assertions.assertEquals(0, res.get(2).getTotalGastadoSillas());
    }

    @Test
    @Sql("classpath:DatosSQL.sql")
    public void obtenerReservasConDetalles() {
        List<Reserva_Detalles_DTO> res = reservaRepo.obtenerDetallesPorReserva("55555");

        res.forEach(System.out::println);
        Assertions.assertNotNull(res);
    }
}
