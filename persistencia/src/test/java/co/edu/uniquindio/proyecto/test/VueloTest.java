package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.Ciudad_Vuelos_DTO;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Departamento;
import co.edu.uniquindio.proyecto.entidades.EstadoVuelo;
import co.edu.uniquindio.proyecto.entidades.Vuelo;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

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
        vuelo.setEstado(EstadoVuelo.CONFIRMADO);

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
        vuelo.setEstado(EstadoVuelo.CONFIRMADO);

        vuelo = vueloRepo.save(vuelo);

        codigo = vuelo.getCodigoVuelo();

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

        vuelo.setAerolinea("Avianca");
        vuelo.setCiudadOrigen(ciudadOrigen);
        vuelo.setCiudadDestino(ciudadDestino);
        vuelo.setEstado(EstadoVuelo.CONFIRMADO);

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
        vuelo1.setEstado(EstadoVuelo.CONFIRMADO);

        vueloRepo.save(vuelo1);

        vuelo2.setAerolinea("Abianka");
        vuelo2.setCiudadOrigen(ciudadOrigen);
        vuelo2.setCiudadDestino(ciudadDestino);
        vuelo2.setEstado(EstadoVuelo.CONFIRMADO);


        vueloRepo.save(vuelo2);

        vuelos = vueloRepo.findAll();

        System.out.print(vuelos);

        Assertions.assertEquals(2, vuelos.size());
    }

    @Test
    public void obtenerVuelosCiudadOrigenTest()
    {
        crearDepartamento();
        crearCiudades();

        Vuelo vuelo1 = new Vuelo();
        Vuelo vuelo2 = new Vuelo();

        List<Vuelo> vuelos;

        vuelo1.setAerolinea("Avianca");
        vuelo1.setCiudadOrigen(ciudadOrigen);
        vuelo1.setCiudadDestino(ciudadDestino);
        vuelo1.setEstado(EstadoVuelo.CONFIRMADO);

        vueloRepo.save(vuelo1);

        vuelo2.setAerolinea("Abianka");
        vuelo2.setCiudadOrigen(ciudadOrigen);
        vuelo2.setCiudadDestino(ciudadDestino);
        vuelo2.setEstado(EstadoVuelo.CONFIRMADO);


        vueloRepo.save(vuelo2);

        vuelos = vueloRepo.obtenerVuelosOrigenCiudad(ciudadOrigen.getNombre());

        System.out.print(vuelos);

        Assertions.assertEquals(2, vuelos.size());
    }

    @Test
    @Sql("classpath:DatosSQL.sql")
    public void obtenerVuelosPorCiudadOrigen() {
        List<Ciudad_Vuelos_DTO> vuelos = vueloRepo.obtenerTotalVuelosPorCiudadOrigen();

        vuelos.forEach(System.out::println);

        Assertions.assertEquals(9, vuelos.size());
        Assertions.assertEquals(0, vuelos.get(0).getTotalRegistros());
        Assertions.assertEquals(1, vuelos.get(2).getTotalRegistros());
        Assertions.assertEquals(2, vuelos.get(3).getTotalRegistros());
        Assertions.assertEquals(1, vuelos.get(5).getTotalRegistros());
    }
}
