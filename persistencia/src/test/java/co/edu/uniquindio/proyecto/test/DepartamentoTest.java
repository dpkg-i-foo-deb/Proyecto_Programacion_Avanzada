package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Departamento;
import co.edu.uniquindio.proyecto.repositorios.DepartamentoRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class DepartamentoTest
{
     @Autowired
     DepartamentoRepo departamentoRepo;

     @Test
     public void crearDepartamentoTest()
      {
          Departamento departamento = new Departamento();

          departamento.setNombre("Quindío");

          departamento = departamentoRepo.save(departamento);

          Assertions.assertNotNull(departamento);
      }

      @Test
      public void eliminarDepartamentoTest()
      {
          Departamento departamento = new Departamento();
          int codigo;

          departamento.setNombre("Quindío");

          departamento = departamentoRepo.save(departamento);

          codigo = departamento.getCodigo_departamento();

          departamentoRepo.delete(departamento);

          departamento = departamentoRepo.findById(codigo).orElse(null);

          Assertions.assertNull(departamento);
      }

      @Test
      public void editarDepartamentoTest()
      {
          Departamento departamento = new Departamento();
          int codigo;

          departamento.setNombre("Quindío");

          departamento = departamentoRepo.save(departamento);

          codigo = departamento.getCodigo_departamento();

          departamento.setNombre("Risaralda");

          departamentoRepo.save(departamento);

          departamento = departamentoRepo.findById(codigo).orElse(null);

          assert departamento != null;
          Assertions.assertEquals("Risaralda", departamento.getNombre());
      }

      @Test
      public void obtenerDepartamentosTest()
      {
          List<Departamento> departamentos;

          Departamento departamento1 = new Departamento();
          Departamento departamento2 = new Departamento();
          Departamento departamento3 = new Departamento();
          Departamento departamento4 = new Departamento();

          departamento1.setNombre("Quindio");
          departamento2.setNombre("Risaralda");
          departamento3.setNombre("Meta");
          departamento4.setNombre("Putumayo");

          departamentoRepo.save(departamento1);
          departamentoRepo.save(departamento2);
          departamentoRepo.save(departamento3);
          departamentoRepo.save(departamento4);

          departamentos = departamentoRepo.findAll();

          System.out.print(departamentos);

          Assertions.assertEquals(4, departamentos.size());
      }
}
