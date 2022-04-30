package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Departamento;
import co.edu.uniquindio.proyecto.servicios.excepciones.CiudadException;
import co.edu.uniquindio.proyecto.servicios.implementacion.CiudadServicioImpl;
import co.edu.uniquindio.proyecto.servicios.implementacion.DepartamentoServicioImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= NegocioApplication.class)
@Transactional
public class CiudadServicioTest
{
    @Autowired
    private CiudadServicioImpl ciudadServicio;

    @Autowired
    private DepartamentoServicioImpl departamentoServicio;

    private Departamento departamento;

    private void crearDepartamento ()
    {
        departamento = new Departamento();

        departamento.setNombre("Quindio");

        departamento = departamentoServicio.registrarDepartamento(departamento);
    }

    @Test
    public void crearCiudadTest()
    {
        crearDepartamento();

        Ciudad ciudad = new Ciudad();

        ciudad.setNombre("Armenia");
        ciudad.setDepartamento(departamento);

        ciudad = ciudadServicio.registrarCiudad(ciudad);

        Assertions.assertNotNull(ciudad);
    }

    @Test
    public void editarCiudadTest()
    {
        crearDepartamento();

        Ciudad ciudad = new Ciudad();

        ciudad.setNombre("Armenia");
        ciudad.setDepartamento(departamento);

        ciudad = ciudadServicio.registrarCiudad(ciudad);

        ciudad.setNombre("Montenegro");

        Ciudad finalCiudad = ciudad;

        Assertions.assertDoesNotThrow(() -> ciudadServicio.editarCiudad(finalCiudad));
    }

    @Test
    public void eliminarCiudadTest()
    {
        crearDepartamento();

        Ciudad ciudad = new Ciudad();

        ciudad.setNombre("Armenia");
        ciudad.setDepartamento(departamento);

        ciudad = ciudadServicio.registrarCiudad(ciudad);

        ciudadServicio.eliminarCiudad(ciudad);

        Ciudad finalCiudad = ciudad;

        Assertions.assertThrows(CiudadException.class,()-> ciudadServicio.obtenerCiudad(finalCiudad.getCodigoCiudad()));
    }

    @Test
    public void obtenerCiudadTest()
    {
        crearDepartamento();

        Ciudad ciudad = new Ciudad();

        ciudad.setNombre("Armenia");
        ciudad.setDepartamento(departamento);

        ciudad = ciudadServicio.registrarCiudad(ciudad);

        Ciudad finalCiudad = ciudad;

        Assertions.assertDoesNotThrow(()-> ciudadServicio.obtenerCiudad(finalCiudad.getCodigoCiudad()));
    }

    @Test
    public void obtenerCiudadesTest()
    {
        crearDepartamento();

        Ciudad ciudad1 = new Ciudad();
        Ciudad ciudad2 = new Ciudad();
        List<Ciudad> ciudades;

        ciudad1.setNombre("Armenia");
        ciudad1.setDepartamento(departamento);

        ciudad2.setNombre("Montenegro");
        ciudad2.setDepartamento(departamento);

        ciudadServicio.registrarCiudad(ciudad1);
        ciudadServicio.registrarCiudad(ciudad2);

        ciudades = ciudadServicio.obtenerCiudades();

        Assertions.assertEquals(2,ciudades.size());
    }
}
