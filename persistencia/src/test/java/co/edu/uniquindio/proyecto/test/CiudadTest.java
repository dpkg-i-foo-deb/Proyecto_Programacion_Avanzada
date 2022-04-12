package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Departamento;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.DepartamentoRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class CiudadTest
{
    @Autowired
    private CiudadRepo ciudadRepo;

    @Autowired
    DepartamentoRepo departamentoRepo;

    private Departamento departamento;

    private void crearDepartamento ()
    {
        departamento = new Departamento();

        departamento.setNombre("Quindio");

        departamento = departamentoRepo.save(departamento);
    }

    @Test
    public void crearCiudadTest()
    {
        Ciudad ciudad = new Ciudad();

        ciudad.setNombre("Armenia");

        crearDepartamento();

        ciudad.setDepartamento(departamento);

        ciudad = ciudadRepo.save(ciudad);

        Assertions.assertNotNull(ciudad);
        Assertions.assertNotNull(ciudad.getDepartamento());
        Assertions.assertEquals(ciudad.getDepartamento(), departamento);
    }

    @Test
    public void eliminarCiudadTest()
    {
        Ciudad ciudad = new Ciudad();
        int codigo = 0;

        ciudad.setNombre("Armenia");

        crearDepartamento();

        ciudad.setDepartamento(departamento);

        ciudad = ciudadRepo.save(ciudad);

        ciudadRepo.delete(ciudad);

        ciudad = ciudadRepo.findById(codigo).orElse(null);

        Assertions.assertNull(ciudad);
    }

    @Test
    public void editarCiudadTest()
    {
        Ciudad ciudad = new Ciudad();
        int codigo;

        ciudad.setNombre("Armenia");

        crearDepartamento();

        ciudad.setDepartamento(departamento);

        ciudad = ciudadRepo.save(ciudad);

        codigo = ciudad.getCodigo_ciudad();

        ciudad.setNombre("Calarca");

        ciudadRepo.save(ciudad);

        ciudad = ciudadRepo.findById(codigo).orElse(null);

        assert ciudad != null;
        Assertions.assertEquals("Calarca", ciudad.getNombre());
    }

    @Test
    public void obtenerCiudadesTest()
    {
        crearDepartamento();

        List<Ciudad> ciudades;

        Ciudad ciudad1 = new Ciudad();
        Ciudad ciudad2 = new Ciudad();
        Ciudad ciudad3 = new Ciudad();
        Ciudad ciudad4 = new Ciudad();

        ciudad1.setNombre("Calarca");
        ciudad2.setNombre("Armenia");
        ciudad3.setNombre("Montenegro");
        ciudad4.setNombre("Circasia");

        ciudad1.setDepartamento(departamento);
        ciudad2.setDepartamento(departamento);
        ciudad3.setDepartamento(departamento);
        ciudad4.setDepartamento(departamento);

        ciudadRepo.save(ciudad1);
        ciudadRepo.save(ciudad2);
        ciudadRepo.save(ciudad3);
        ciudadRepo.save(ciudad4);

        ciudades = ciudadRepo.findAll();

        System.out.print(ciudades);
    }

}
