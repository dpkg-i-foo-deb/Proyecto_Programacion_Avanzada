package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Cama;
import co.edu.uniquindio.proyecto.repositorios.CamaRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class CamaTest
{
    @Autowired
    private CamaRepo camaRepo;

    @Test
    public void registrarCamaTest()
    {
        Cama cama = new Cama();
        cama.setTipo("simple");

        Cama camaGuardada = camaRepo.save(cama);

        Assertions.assertNotNull(camaGuardada);
    }

    @Test
    public void eliminarCamaTest()
    {
        Cama cama = new Cama();
        cama.setTipo("simple");
        cama.setCodigo_cama(1234);

        camaRepo.delete(cama);

        Cama camaBuscada = camaRepo.findById(1234).orElse(null);

        Assertions.assertNull(camaBuscada);
    }

    @Test
    public void actualizarCamaTest()
    {
        Cama cama = new Cama();
        cama.setTipo("simple");

        Cama camaGuardada = camaRepo.save(cama);

        int codigo = camaGuardada.getCodigo_cama();

        camaGuardada.setTipo("doble");

        camaRepo.save(camaGuardada);

        Cama camaBuscada = camaRepo.findById(codigo).orElse(null);

        Assertions.assertEquals("doble",camaBuscada.getTipo());
    }

    @Test
    public void obtenerCamas()
    {
        Cama cama1 = new Cama();
        Cama cama2 = new Cama();
        Cama cama3 = new Cama();
        Cama cama4 = new Cama();

        cama1.setTipo("simple");
        cama2.setTipo("doble");
        cama3.setTipo("simple");
        cama4.setTipo("doble");

        camaRepo.save(cama1);
        camaRepo.save(cama2);
        camaRepo.save(cama3);
        camaRepo.save(cama4);

        List<Cama> listaCamas = camaRepo.findAll();

        System.out.print(listaCamas);
    }
}
