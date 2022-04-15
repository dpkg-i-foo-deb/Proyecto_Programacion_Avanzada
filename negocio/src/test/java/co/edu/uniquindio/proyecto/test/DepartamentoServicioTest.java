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
    public void obtenerDepartamentosTest()
    {
        Departamento departamento1 = new Departamento();
        Departamento departamento2 = new Departamento();
        List<Departamento> departamentos;

        departamento1.setNombre("Quindío");
        departamento2.setNombre("Meta");

        departamentoServicio.registrarDepartamento(departamento1);
        departamentoServicio.registrarDepartamento(departamento2);

        departamentos=departamentoServicio.obtenerDepartamentos();

        Assertions.assertNotNull(departamentos);
        Assertions.assertEquals(2, departamentos.size());

    }
}
