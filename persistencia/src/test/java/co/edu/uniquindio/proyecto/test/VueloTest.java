package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Departamento;
import co.edu.uniquindio.proyecto.entidades.Vuelo;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class VueloTest
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

    @Test
    public void crearVueloTest()
    {
        crearDepartamento();
        crearCiudades();

        Vuelo vuelo = new Vuelo();

        vuelo.setAerolinea("Avianca");
        vuelo.setCiudadOrigen(ciudadOrigen);
        vuelo.setCiudadDestino(ciudadDestino);

        vuelo = vueloRepo.save(vuelo);

        Assertions.assertNotNull(vuelo);
    }

    @Test
    public void eliminarVueloTest()
    {
        crearDepartamento();
        crearCiudades();

        Vuelo vuelo = new Vuelo();
        int codigo;

        vuelo.setAerolinea("Avianca");
        vuelo.setCiudadOrigen(ciudadOrigen);
        vuelo.setCiudadDestino(ciudadDestino);

        vuelo = vueloRepo.save(vuelo);

        codigo = vuelo.getCodigo();

        vueloRepo.delete(vuelo);

        vuelo = vueloRepo.findById(codigo).orElse(null);

        Assertions.assertNull(vuelo);
    }

    @Test
    public void editarVueloTest()
    {
        crearDepartamento();
        crearCiudades();

        Vuelo vuelo = new Vuelo();
        int codigo;

        vuelo.setAerolinea("Avianca");
        vuelo.setCiudadOrigen(ciudadOrigen);
        vuelo.setCiudadDestino(ciudadDestino);

        vuelo = vueloRepo.save(vuelo);

        vuelo.setAerolinea("Abianka");

        vuelo = vueloRepo.save(vuelo);

        Assertions.assertEquals("Abianka", vuelo.getAerolinea());
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

        vueloRepo.save(vuelo1);

        vuelo2.setAerolinea("Abianka");
        vuelo2.setCiudadOrigen(ciudadOrigen);
        vuelo2.setCiudadDestino(ciudadDestino);

        vueloRepo.save(vuelo2);

        vuelos = vueloRepo.findAll();

        System.out.print(vuelos);
    }
}
