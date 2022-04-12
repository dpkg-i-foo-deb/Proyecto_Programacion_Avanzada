package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Caracteristica;
import co.edu.uniquindio.proyecto.repositorios.CaracteristicaRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class CaracteristicaTest {
    @Autowired
    private CaracteristicaRepo caracteristicaRepo;

    @Test
    public void registrarCaracteristicaTest() {
        Caracteristica caracteristica = new Caracteristica();
        caracteristica.setDescripcion("piscina");

        Caracteristica caracteristicaGuardada = caracteristicaRepo.save(caracteristica);

        Assertions.assertNotNull(caracteristicaGuardada);
    }

    @Test
    public void eliminarCaracteristicaTest() {

        Caracteristica caracteristica = new Caracteristica();
        int codigo;

        caracteristica.setDescripcion("piscina");

        Caracteristica caracteristicaGuardada = caracteristicaRepo.save(caracteristica);

        codigo = caracteristicaGuardada.getCodigo();

        caracteristicaRepo.delete(caracteristica);

        caracteristicaGuardada = caracteristicaRepo.findById(codigo).orElse(null);

        Assertions.assertNull(caracteristicaGuardada);
    }

    @Test
    public void actualizarCaracteristicaTest()
    {
        Caracteristica caracteristica = new Caracteristica();
        int codigo;

        caracteristica.setDescripcion("piscina");

        Caracteristica caracteristicaGuardada = caracteristicaRepo.save(caracteristica);

        codigo= caracteristicaGuardada.getCodigo();

        caracteristicaGuardada.setDescripcion("gimnasio");

        caracteristicaRepo.save(caracteristica);

        Caracteristica caracteristicaBuscada = caracteristicaRepo.findById(codigo).orElse(null);

        assert caracteristicaBuscada != null;
        Assertions.assertEquals(caracteristicaBuscada.getDescripcion(),"gimnasio");
    }

    @Test
    public void obtenerCaracteristicasTest()
    {
        Caracteristica caracteristica1 = new Caracteristica();
        Caracteristica caracteristica2 = new Caracteristica();
        Caracteristica caracteristica3 = new Caracteristica();
        Caracteristica caracteristica4 = new Caracteristica();

        List<Caracteristica> caracteristicas;

        caracteristica1.setDescripcion("piscina");
        caracteristica2.setDescripcion("gimnasio");
        caracteristica3.setDescripcion("bolos");
        caracteristica4.setDescripcion("asado");

        caracteristicaRepo.save(caracteristica1);
        caracteristicaRepo.save(caracteristica2);
        caracteristicaRepo.save(caracteristica3);
        caracteristicaRepo.save(caracteristica4);

        caracteristicas = caracteristicaRepo.findAll();

        System.out.print(caracteristicas);

        Assertions.assertEquals(4, caracteristicas.size());
    }
}
