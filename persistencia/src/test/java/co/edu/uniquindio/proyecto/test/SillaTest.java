package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Departamento;
import co.edu.uniquindio.proyecto.entidades.Silla;
import co.edu.uniquindio.proyecto.entidades.Vuelo;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.DepartamentoRepo;
import co.edu.uniquindio.proyecto.repositorios.SillaRepo;
import co.edu.uniquindio.proyecto.repositorios.VueloRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class SillaTest
{
    @Autowired
    private CiudadRepo ciudadRepo;
    @Autowired
    private DepartamentoRepo departamentoRepo;
    @Autowired
    private VueloRepo vueloRepo;

    private Ciudad ciudadOrigen;
    private Ciudad ciudadDestino;
    private Departamento departamento;
    private Vuelo vuelo;

    private void crearDepartamento()
    {
        departamento = new Departamento();
        departamento.setNombre("Quindio");

        departamentoRepo.save(departamento);
    }

    private void crearCiudades()
    {
        ciudadOrigen = new Ciudad();
        ciudadOrigen.setNombre("Armenia");

        ciudadOrigen.setDepartamento(departamento);

        ciudadRepo.save(ciudadOrigen);

        ciudadDestino = new Ciudad();
        ciudadDestino.setNombre("Montenegro");

        ciudadDestino.setDepartamento(departamento);

        ciudadRepo.save(ciudadDestino);
    }

    private void crearVuelo()
    {
        vuelo = new Vuelo();
        vuelo.setAerolinea("Avianca");
        vuelo.setCiudadOrigen(ciudadOrigen);
        vuelo.setCiudadDestino(ciudadDestino);

        vuelo = vueloRepo.save(vuelo);
    }

    @Autowired
    private SillaRepo sillaRepo;

    @Test
    public void crearSillaTest()
    {
        crearDepartamento();
        crearCiudades();
        crearVuelo();

        Silla silla = new Silla();
        silla.setVuelo(vuelo);

        Silla sillaGuardada;

        silla.setPrecio(100000.00);

        sillaGuardada = sillaRepo.save(silla);

        Assertions.assertNotNull(sillaGuardada);

    }

    @Test
    public void eliminarSillaTest()
    {
        crearDepartamento();
        crearCiudades();
        crearVuelo();

        Silla silla = new Silla();
        int codigo;

        Silla sillaGuardada;

        silla.setPrecio(100000.00);
        silla.setVuelo(vuelo);
        sillaGuardada = sillaRepo.save(silla);

        codigo = sillaGuardada.getCodigoSilla();

        sillaRepo.delete(sillaGuardada);

        sillaGuardada= sillaRepo.findById(codigo).orElse(null);

        Assertions.assertNull(sillaGuardada);
    }

    @Test
    public void editarSillaTest()
    {
        crearDepartamento();
        crearCiudades();
        crearVuelo();

        Silla silla = new Silla();

        Silla sillaGuardada;

        silla.setPrecio(100000.00);
        silla.setVuelo(vuelo);

        sillaGuardada = sillaRepo.save(silla);

        sillaGuardada.setPrecio(10.00);

        sillaGuardada=sillaRepo.save(sillaGuardada);

        Assertions.assertEquals(sillaGuardada.getPrecio(),10.00);
    }

    @Test
    public void obtenerSillasTest()
    {
        crearDepartamento();
        crearCiudades();
        crearVuelo();

        Silla silla1 = new Silla();
        Silla silla2 = new Silla();
        Silla silla3 = new Silla();
        Silla silla4 = new Silla();


        List<Silla> sillas;

        silla1.setPrecio(10.00);
        silla2.setPrecio(20.00);
        silla3.setPrecio(30.00);
        silla4.setPrecio(40.00);

        silla1.setVuelo(vuelo);
        silla2.setVuelo(vuelo);
        silla3.setVuelo(vuelo);
        silla4.setVuelo(vuelo);


        sillaRepo.save(silla1);
        sillaRepo.save(silla2);
        sillaRepo.save(silla3);
        sillaRepo.save(silla4);

        sillas = sillaRepo.findAll();

        System.out.print(sillas);

        Assertions.assertEquals(4, sillas.size());
    }

}
