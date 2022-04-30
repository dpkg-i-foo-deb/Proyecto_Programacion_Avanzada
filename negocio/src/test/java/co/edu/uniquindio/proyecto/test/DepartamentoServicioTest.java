package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Departamento;
import co.edu.uniquindio.proyecto.servicios.excepciones.DepartamentoException;
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
public class DepartamentoServicioTest
{
    @Autowired
    private DepartamentoServicioImpl departamentoServicio;

    @Test
    public void crearDepartamentoTest()
    {
        Departamento departamento = new Departamento();

        departamento.setNombre("Quindío");

        departamentoServicio.registrarDepartamento(departamento);

        Assertions.assertNotNull(departamento);
    }

    @Test
    public void eliminarDepartamentoTest()
    {
        Departamento departamento = new Departamento();
        int codigo;

        departamento.setNombre("Quindío");

        departamento = departamentoServicio.registrarDepartamento(departamento);

        codigo = departamento.getCodigoDepartamento();

        departamentoServicio.eliminarDepartamento(departamento);

        Assertions.assertThrows(DepartamentoException.class,() ->
                departamentoServicio.obtenerDepartamento(codigo));
    }

    @Test
    public void editarDepartamentoTest()
    {
        Departamento departamento = new Departamento();

        departamento.setNombre("Quindío");

        departamento = departamentoServicio.registrarDepartamento(departamento);

        departamento.setNombre("Meta");

        Departamento finalDepartamento = departamento;
        Assertions.assertDoesNotThrow(() -> departamentoServicio.editarDepartamento(finalDepartamento));
    }

    @Test
    public void obtenerDepartamentoTest()
    {
        Departamento departamento = new Departamento();

        departamento.setNombre("Quindío");

        departamento = departamentoServicio.registrarDepartamento(departamento);

        Departamento finalDepartamento = departamento;
        Assertions.assertDoesNotThrow(()->departamentoServicio.obtenerDepartamento(finalDepartamento.getCodigoDepartamento()));
    }

    @Test
    public void obtenerDepartamentosTest()
    {
        List<Departamento> departamentos = departamentoServicio.obtenerDepartamentos();

        departamentos.forEach(System.out::println);

        Assertions.assertNotNull(departamentos);
        Assertions.assertEquals(3, departamentos.size());
    }
}
