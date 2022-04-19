package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Departamento;
import co.edu.uniquindio.proyecto.entidades.Vuelo;
import co.edu.uniquindio.proyecto.servicios.excepciones.VueloException;
import co.edu.uniquindio.proyecto.servicios.implementacion.CiudadServicioImpl;
import co.edu.uniquindio.proyecto.servicios.implementacion.DepartamentoServicioImpl;
import co.edu.uniquindio.proyecto.servicios.implementacion.VueloServicioImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class VueloServicioTest
{
    @Autowired
    private DepartamentoServicioImpl departamentoServicio;

    @Autowired
    private CiudadServicioImpl ciudadServicio;

    @Autowired
    private VueloServicioImpl vueloServicio;

    private Ciudad ciudadOrigen;
    private Ciudad ciudadDestino;
    private Departamento departamento;

    private void crearDepartamento()
    {
        departamento = new Departamento();
        departamento.setNombre("Quindio");

        departamentoServicio.registrarDepartamento(departamento);
    }

    private void crearCiudades()
    {
        ciudadOrigen = new Ciudad();
        ciudadOrigen.setNombre("Armenia");

        ciudadOrigen.setDepartamento(departamento);

        ciudadServicio.registrarCiudad(ciudadOrigen);

        ciudadDestino = new Ciudad();
        ciudadDestino.setNombre("Montenegro");

        ciudadDestino.setDepartamento(departamento);

        ciudadServicio.registrarCiudad(ciudadDestino);
    }

    @Test
    public void crearVueloTest()
    {
        crearDepartamento();
        crearCiudades();

        Vuelo vuelo = new Vuelo();

        vuelo.setAerolinea("Avianca");
        vuelo.setCiudadOrigen(ciudadOrigen);
        vuelo.setCiudadDestino(ciudadDestino);

        vuelo = vueloServicio.crearVuelo(vuelo);

        Assertions.assertNotNull(vuelo);
    }

    @Test
    public void obtenerVueloTest()
    {
        crearDepartamento();
        crearCiudades();

        Vuelo vuelo = new Vuelo();

        vuelo.setAerolinea("Avianca");
        vuelo.setCiudadOrigen(ciudadOrigen);
        vuelo.setCiudadDestino(ciudadDestino);

        vuelo = vueloServicio.crearVuelo(vuelo);

        Vuelo finalVuelo = vuelo;

        Assertions.assertDoesNotThrow(()-> vueloServicio.obtenerVuelo(finalVuelo.getCodigoVuelo()));
    }

    @Test
    public void editarVueloTest()
    {
        crearDepartamento();
        crearCiudades();

        Vuelo vuelo = new Vuelo();

        vuelo.setAerolinea("Avianca");
        vuelo.setCiudadOrigen(ciudadOrigen);
        vuelo.setCiudadDestino(ciudadDestino);

        vuelo = vueloServicio.crearVuelo(vuelo);

        vuelo.setAerolinea("Avianka");

        Vuelo finalVuelo = vuelo;

        Assertions.assertDoesNotThrow(()-> vueloServicio.editarVuelo(finalVuelo));
    }

    @Test
    public void eliminarVueloTest()
    {
        crearDepartamento();
        crearCiudades();

        Vuelo vuelo = new Vuelo();

        vuelo.setAerolinea("Avianca");
        vuelo.setCiudadOrigen(ciudadOrigen);
        vuelo.setCiudadDestino(ciudadDestino);

        vuelo = vueloServicio.crearVuelo(vuelo);

        vueloServicio.eliminarVuelo(vuelo);

        Vuelo finalVuelo = vuelo;

        Assertions.assertThrows(VueloException.class,()-> vueloServicio.obtenerVuelo(finalVuelo.getCodigoVuelo()));
    }

    @Test
    public void obtenerVuelosTest()
    {
        crearDepartamento();
        crearCiudades();

        Vuelo vuelo1 = new Vuelo();
        Vuelo vuelo2 = new Vuelo();
        List<Vuelo> vuelos;

        vuelo1.setAerolinea("Avianca");
        vuelo1.setCiudadOrigen(ciudadOrigen);
        vuelo1.setCiudadDestino(ciudadDestino);

        vuelo2.setAerolinea("Avianka");
        vuelo2.setCiudadOrigen(ciudadOrigen);
        vuelo2.setCiudadDestino(ciudadDestino);

        vueloServicio.crearVuelo(vuelo1);
        vueloServicio.crearVuelo(vuelo2);

        vuelos=vueloServicio.obtenerVuelos();

        Assertions.assertEquals(2,vuelos.size());
    }

}