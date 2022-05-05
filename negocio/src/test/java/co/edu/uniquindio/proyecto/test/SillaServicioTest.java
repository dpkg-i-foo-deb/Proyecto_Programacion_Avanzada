package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.excepciones.SillaException;
import co.edu.uniquindio.proyecto.servicios.excepciones.VueloException;
import co.edu.uniquindio.proyecto.servicios.implementacion.CiudadServicioImpl;
import co.edu.uniquindio.proyecto.servicios.implementacion.DepartamentoServicioImpl;
import co.edu.uniquindio.proyecto.servicios.implementacion.SillaServicioImpl;
import co.edu.uniquindio.proyecto.servicios.implementacion.VueloServicioImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class SillaServicioTest
{
    @Autowired
    private SillaServicioImpl sillaServicio;

    @Autowired
    private CiudadServicioImpl ciudadServicio;

    @Autowired
    private DepartamentoServicioImpl departamentoServicio;

    @Autowired
    private VueloServicioImpl vueloServicio;

    private Ciudad ciudadOrigen;
    private Ciudad ciudadDestino;
    private Departamento departamento;

    private Vuelo vuelo;

    private void crearDepartamento()
    {
        departamento = new Departamento();
        departamento.setNombre("Quindio");

        departamentoServicio.registrarDepartamento(departamento);
    }

    private void crearCiudad()
    {
        ciudadOrigen = new Ciudad();
        ciudadOrigen.setNombre("Armenia");
        ciudadOrigen.setDepartamento(departamento);

        ciudadDestino = new Ciudad();
        ciudadDestino.setNombre("Montenegro");
        ciudadDestino.setDepartamento(departamento);

        ciudadServicio.registrarCiudad(ciudadOrigen);
        ciudadServicio.registrarCiudad(ciudadDestino);
    }

    private void crearVuelo()
    {
        vuelo = new Vuelo();
        vuelo.setCiudadOrigen(ciudadOrigen);
        vuelo.setCiudadDestino(ciudadDestino);
        vuelo.setAerolinea("Avianca");
        vuelo.setEstado(EstadoVuelo.CONFIRMADO);

        try {
            vueloServicio.crearVuelo(vuelo);
        } catch (VueloException e) {
            System.out.print(e.getMessage());
        }
    }

    @Test
    public void crearSillaTest()
    {
        crearDepartamento();
        crearCiudad();
        crearVuelo();

        Silla silla = new Silla();
        silla.setVuelo(vuelo);
        silla.setPrecio(10.0);

        silla = sillaServicio.crearSilla(silla);

        Assertions.assertNotNull(silla);
    }

    @Test
    public void editarSillaTest()
    {
        crearDepartamento();
        crearCiudad();
        crearVuelo();

        Silla silla = new Silla();
        silla.setVuelo(vuelo);
        silla.setPrecio(10.0);

        silla = sillaServicio.crearSilla(silla);

        silla.setPrecio(20.0);

        try
        {
            silla = sillaServicio.editarSilla(silla);
        } catch (SillaException e)
        {
            System.out.print(e.getMessage());
        }

        Assertions.assertEquals(20.0, silla.getPrecio());
    }

    @Test
    public void eliminarSillaTest()
    {
        crearDepartamento();
        crearCiudad();
        crearVuelo();

        Silla silla = new Silla();
        Silla sillaCopia;
        silla.setVuelo(vuelo);
        silla.setPrecio(10.0);

        silla = sillaServicio.crearSilla(silla);
        sillaCopia=silla;

        sillaServicio.eliminarSilla(silla);

        Assertions.assertFalse(sillaServicio.obtenerSillas().contains(sillaCopia));
    }

    @Test
    public void obtenerSillasTest()
    {
        crearDepartamento();
        crearCiudad();
        crearVuelo();

        Silla silla1 = new Silla();
        silla1.setVuelo(vuelo);
        silla1.setPrecio(10.0);

        Silla silla2 = new Silla();
        silla2.setVuelo(vuelo);
        silla2.setPrecio(10.0);

        silla2 = sillaServicio.crearSilla(silla2);
        silla1 = sillaServicio.crearSilla(silla1);

        Assertions.assertTrue(sillaServicio.obtenerSillas().contains(silla1));
        Assertions.assertTrue(sillaServicio.obtenerSillas().contains(silla2));

    }
}
