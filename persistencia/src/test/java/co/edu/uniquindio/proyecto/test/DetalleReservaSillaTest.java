package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.entidades.intermediate.Detalle_Reserva_Silla;
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
public class DetalleReservaSillaTest {
    @Autowired
    private DepartamentoRepo departamentoRepo;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private VueloRepo vueloRepo;

    @Autowired
    private SillaRepo sillaRepo;

    @Autowired
    private ReservaRepo reservaRepo;

    @Autowired
    private DetalleReservaSillaRepo detalleReservaSillaRepo;

    private Departamento depto;
    private Ciudad ciudadOrigen;
    private Ciudad ciudadDestino;
    private Persona_Usuario usuario;
    private Vuelo vuelo;
    private Silla silla1;
    private Silla silla2;
    private Silla silla3;
    private Reserva reserva;

    private void crearDepartamento() {
        depto = new Departamento("Quindío");
        departamentoRepo.save(depto);
    }

    private void crearCiudades() {
        ciudadOrigen = new Ciudad("Armenia", depto);
        ciudadRepo.save(ciudadOrigen);

        ciudadDestino = new Ciudad("Calarcá", depto);
        ciudadRepo.save(ciudadDestino);
    }

    private void crearUsuario() {
        usuario = new Persona_Usuario(
                "09876",
                "Usuario",
                "usuario@email.com",
                "usuario123", ciudadOrigen
        );
        usuarioRepo.save(usuario);
    }

    private void crearVuelo() {
        vuelo = new Vuelo("Avianca", ciudadOrigen, ciudadDestino);
        vueloRepo.save(vuelo);
    }

    private void crearSillas() {
        silla1 = new Silla(8.00, vuelo);
        sillaRepo.save(silla1);
        silla2 = new Silla(8.00, vuelo);
        sillaRepo.save(silla2);
        silla3 = new Silla(8.00, vuelo);
        sillaRepo.save(silla3);
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
        crearCiudades();
        crearUsuario();
        crearVuelo();
        crearSillas();
        crearReserva();

        Detalle_Reserva_Silla detalle = new Detalle_Reserva_Silla(reserva, silla1, 20.00);
        Detalle_Reserva_Silla detalleGuardado = detalleReservaSillaRepo.save(detalle);

        Assertions.assertNotNull(detalleGuardado);
        Assertions.assertEquals(detalleGuardado.getCodigo(), detalle.getCodigo());

        System.out.println(detalleGuardado);
        System.out.println(detalle);
    }

    @Test
    public void eliminarDetalleTest() {
        crearDepartamento();
        crearCiudades();
        crearUsuario();
        crearVuelo();
        crearSillas();
        crearReserva();

        Detalle_Reserva_Silla detalle = new Detalle_Reserva_Silla(reserva, silla1, 20.00);
        detalleReservaSillaRepo.save(detalle);
        int codigo = detalle.getCodigo();

        detalleReservaSillaRepo.delete(detalle);

        Optional<Detalle_Reserva_Silla> detalleRecuperado = detalleReservaSillaRepo.findById(codigo);

        Assertions.assertFalse(detalleRecuperado.isPresent());
    }

    @Test
    public void editarDetalleTest() {
        crearDepartamento();
        crearCiudades();
        crearUsuario();
        crearVuelo();
        crearSillas();
        crearReserva();

        Detalle_Reserva_Silla detalle = new Detalle_Reserva_Silla(reserva, silla1, 20.00);
        detalle = detalleReservaSillaRepo.save(detalle);

        detalle.setPrecio(35.22);

        Detalle_Reserva_Silla detalleModificado = detalleReservaSillaRepo.save(detalle);

        Assertions.assertNotNull(detalleModificado);
        Assertions.assertEquals(detalle.getPrecio(), detalleModificado.getPrecio());
    }

    @Test
    public void obtenerDetalleTest() {
        crearDepartamento();
        crearCiudades();
        crearUsuario();
        crearVuelo();
        crearSillas();
        crearReserva();

        Detalle_Reserva_Silla detalle1 = new Detalle_Reserva_Silla(reserva, silla1, 20.00);
        detalleReservaSillaRepo.save(detalle1);

        Detalle_Reserva_Silla detalle2 = new Detalle_Reserva_Silla(reserva, silla2, 20.00);
        detalleReservaSillaRepo.save(detalle2);

        Detalle_Reserva_Silla detalle3 = new Detalle_Reserva_Silla(reserva, silla3, 20.00);
        detalleReservaSillaRepo.save(detalle3);

        List<Detalle_Reserva_Silla> detalles = detalleReservaSillaRepo.findAll();

        Assertions.assertEquals(3, detalles.size());
    }
}
