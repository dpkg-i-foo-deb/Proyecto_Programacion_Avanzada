package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioTest
{
    @Autowired
    private UsuarioRepo usuarioRepo;
    @Autowired
    private CiudadRepo ciudadRepo;
    @Autowired
    private DepartamentoRepo departamentoRepo;
    @Autowired
    private AdministradorHotelRepo administradorHotelRepo;
    @Autowired
    private HotelRepo hotelRepo;
    @Autowired
    private ReservaRepo reservaRepo;

    private Ciudad ciudad;
    private Departamento departamento;
    private Persona_Administrador_Hotel adminHotel;
    private Persona_Usuario usuario;

    private void crearDepartamento()
    {
        departamento = new Departamento();
        departamento.setNombre("Quindio");

        departamentoRepo.save(departamento);
    }

    private void crearCiudad()
    {
        ciudad = new Ciudad();
        ciudad.setNombre("Armenia");

        ciudad.setDepartamento(departamento);

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
        Hotel hotel = new Hotel("Hotelito", (short) 5, "Calle 45-25", ciudad, adminHotel, EstadoHotel.DISPONIBLE);
        hotelRepo.save(hotel);
    }

    @Test
    public void crearUsuarioTest()
    {
        crearDepartamento();
        crearCiudad();

        Persona_Usuario usuario = new Persona_Usuario();
        usuario.setNombreCompleto("Chukimamberto Pipicano");
        usuario.setCedula("12345");
        usuario.setEmail("someone@example.com");
        usuario.setContrasena("54321");

        usuario.setCiudad(ciudad);

        usuario = usuarioRepo.save(usuario);

        Assertions.assertNotNull(usuario);
    }

    @Test
    public void eliminarUsuarioTest()
    {
        crearDepartamento();
        crearCiudad();
        String cedula;

        Persona_Usuario usuario = new Persona_Usuario();
        usuario.setNombreCompleto("Chukimamberto Pipicano");
        usuario.setCedula("12345");
        usuario.setEmail("someone@example.com");
        usuario.setContrasena("54321");

        usuario.setCiudad(ciudad);

        usuario = usuarioRepo.save(usuario);

        cedula = usuario.getCedula();

        usuarioRepo.delete(usuario);

        usuario = usuarioRepo.getPersona_UsuarioByCedula(cedula);

        Assertions.assertNull(usuario);
    }

    @Test
    public void editarUsuarioTest()
    {
        crearDepartamento();
        crearCiudad();

        Persona_Usuario usuario = new Persona_Usuario();
        usuario.setNombreCompleto("Chukimamberto Pipicano");
        usuario.setCedula("12345");
        usuario.setEmail("someone@example.com");
        usuario.setContrasena("54321");

        usuario.setCiudad(ciudad);

        usuario = usuarioRepo.save(usuario);

        usuario.setNombreCompleto("Maricarmen Chamizo");

        usuario = usuarioRepo.save(usuario);

        Assertions.assertEquals("Maricarmen Chamizo",usuario.getNombreCompleto());
    }

    @Test
    public void obtenerUsuariosTest()
    {
        crearDepartamento();
        crearCiudad();

        List<Persona_Usuario> usuarios;

        Persona_Usuario usuario1 = new Persona_Usuario();
        Persona_Usuario usuario2 = new Persona_Usuario();

        usuario1.setNombreCompleto("Chukimamberto Pipicano");
        usuario1.setCedula("12345");
        usuario1.setEmail("someone@example.com");
        usuario1.setContrasena("54321");

        usuario1.setCiudad(ciudad);

        usuarioRepo.save(usuario1);

        usuario2.setNombreCompleto("Maricarmen Chamizo");
        usuario2.setCedula("192994");
        usuario2.setEmail("someone2@example.com");
        usuario2.setContrasena("6567678");

        usuario2.setCiudad(ciudad);

        usuarioRepo.save(usuario2);

        usuarios = usuarioRepo.findAll();

        System.out.print(usuarios);

        Assertions.assertEquals(2, usuarios.size());
    }

    @Test
    public void buscarUsuariosPorNombre() {
        crearDepartamento();
        crearCiudad();

        Persona_Usuario usuario1 = new Persona_Usuario();
        Persona_Usuario usuario2 = new Persona_Usuario();
        Persona_Usuario usuario3 = new Persona_Usuario();

        usuario1.setNombreCompleto("Pepito Pérez");
        usuario1.setCedula("12345");
        usuario1.setEmail("someone@example.com");
        usuario1.setContrasena("54321");
        usuario1.setCiudad(ciudad);
        usuarioRepo.save(usuario1);

        usuario2.setNombreCompleto("Maricarmen Chamizo");
        usuario2.setCedula("192994");
        usuario2.setEmail("someone2@example.com");
        usuario2.setContrasena("6567678");
        usuario2.setCiudad(ciudad);
        usuarioRepo.save(usuario2);

        usuario3.setNombreCompleto("Pepito Pérez");
        usuario3.setCedula("223344");
        usuario3.setEmail("pepito@example.com");
        usuario3.setContrasena("pepe123");
        usuario3.setCiudad(ciudad);
        usuarioRepo.save(usuario3);

        List<Persona_Usuario> usuarios = usuarioRepo.findAllByNombreCompleto("Pepito Pérez");

        System.out.print(usuarios);

        Assertions.assertEquals(2, usuarios.size());
    }

    @Test
    public void obtenerUsuariosPagina() {
        crearDepartamento();
        crearCiudad();

        Persona_Usuario usuario1 = new Persona_Usuario();
        Persona_Usuario usuario2 = new Persona_Usuario();
        Persona_Usuario usuario3 = new Persona_Usuario();

        usuario1.setNombreCompleto("Pepito Pérez");
        usuario1.setCedula("12345");
        usuario1.setEmail("someone@example.com");
        usuario1.setContrasena("54321");
        usuario1.setCiudad(ciudad);
        usuarioRepo.save(usuario1);

        usuario2.setNombreCompleto("Maricarmen Chamizo");
        usuario2.setCedula("192994");
        usuario2.setEmail("someone2@example.com");
        usuario2.setContrasena("6567678");
        usuario2.setCiudad(ciudad);
        usuarioRepo.save(usuario2);

        usuario3.setNombreCompleto("Pepito Pérez");
        usuario3.setCedula("223344");
        usuario3.setEmail("pepito@example.com");
        usuario3.setContrasena("pepe123");
        usuario3.setCiudad(ciudad);
        usuarioRepo.save(usuario3);

        Page<Persona_Usuario> usuarios = usuarioRepo.findAll(PageRequest.of(0, 2));

        System.out.print(usuarios);

        Assertions.assertEquals(2, usuarios.toList().size());
    }

    @Test
    public void obtenerReservasUsuario() {
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

        reserva = new Reserva(
                Date.valueOf(LocalDate.now().plusDays(20)),
                Date.valueOf(LocalDate.now().plusMonths(1)),
                Date.valueOf(LocalDate.now()),
                EstadoReserva.CONFIRMADA, usuario
        );
        reservaRepo.save(reserva);

        List<Reserva> reservas = usuarioRepo.obtenerReservasByEmail(usuario.getEmail());

        Assertions.assertEquals(2, reservas.size());
    }

    @Test
    public void obtenerUsuariosTelefono() {
        crearDepartamento();
        crearCiudad();

        Persona_Usuario usuario1 = new Persona_Usuario();
        Persona_Usuario usuario2 = new Persona_Usuario();

        usuario1.setNombreCompleto("Pepito Pérez");
        usuario1.setCedula("12345");
        usuario1.setEmail("someone@example.com");
        usuario1.setContrasena("54321");
        usuario1.setCiudad(ciudad);

        usuario1.getTelefonos().add("3226728810");

        usuarioRepo.save(usuario1);

        usuario2.setNombreCompleto("Maricarmen Chamizo");
        usuario2.setCedula("192994");
        usuario2.setEmail("someone2@example.com");
        usuario2.setContrasena("6567678");
        usuario2.setCiudad(ciudad);
        usuario2.getTelefonos().add("3226728810");

        usuarioRepo.save(usuario2);


        List<Persona_Usuario> usuarios = usuarioRepo.obtenerUsuariosTelefono("3226728810");
        System.out.print(usuarios);
        Assertions.assertEquals(2, usuarios.size());

        usuarios = usuarioRepo.obtenerUsuariosTelefono("3226728811");
        Assertions.assertEquals(0, usuarios.size());
    }


}
