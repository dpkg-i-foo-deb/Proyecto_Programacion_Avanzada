package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Silla;
import co.edu.uniquindio.proyecto.entidades.embedded.Silla_ID;
import co.edu.uniquindio.proyecto.repositorios.SillaRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class SillaTest
{

    @Autowired
    SillaRepo sillaRepo;

    @Test
    public void crearSillaTest()
    {
        Silla silla = new Silla();
        Silla_ID id= new Silla_ID();

        id.setCodigoVuelo(1);
        Silla sillaGuardada;

        silla.setPrecio(100000.00);
        silla.setIdSilla(id);

        sillaGuardada = sillaRepo.save(silla);

        Assertions.assertNotNull(sillaGuardada);

    }

    @Test
    public void eliminarSillaTest()
    {
        Silla silla = new Silla();
        Silla_ID id= new Silla_ID();

        id.setCodigoVuelo(1);
        Silla sillaGuardada;

        silla.setPrecio(100000.00);
        silla.setIdSilla(id);

        sillaGuardada = sillaRepo.save(silla);

        id=sillaGuardada.getIdSilla();

        sillaRepo.delete(sillaGuardada);

        sillaGuardada= sillaRepo.findByIdSilla(id);

        Assertions.assertNull(sillaGuardada);
    }

    @Test
    public void editarSillaTest()
    {
        Silla silla = new Silla();
        Silla_ID id= new Silla_ID();

        id.setCodigoVuelo(1);
        Silla sillaGuardada;

        silla.setPrecio(100000.00);
        silla.setIdSilla(id);

        sillaGuardada = sillaRepo.save(silla);

        sillaGuardada.setPrecio(10.00);

        sillaGuardada=sillaRepo.save(sillaGuardada);

        Assertions.assertEquals(sillaGuardada.getPrecio(),10.00);
    }

    @Test
    public void obtenerSillasTest() {
        Silla silla1 = new Silla();
        Silla silla2 = new Silla();
        Silla silla3 = new Silla();
        Silla silla4 = new Silla();

        Silla_ID id1= new Silla_ID();
        Silla_ID id2= new Silla_ID();
        Silla_ID id3= new Silla_ID();
        Silla_ID id4= new Silla_ID();

        List<Silla> sillas;

        silla1.setPrecio(10.00);
        silla2.setPrecio(20.00);
        silla3.setPrecio(30.00);
        silla4.setPrecio(40.00);

        silla1.setIdSilla(id1);
        silla2.setIdSilla(id2);
        silla3.setIdSilla(id3);
        silla4.setIdSilla(id4);

        sillaRepo.save(silla1);
        sillaRepo.save(silla2);
        sillaRepo.save(silla3);
        sillaRepo.save(silla4);

        sillas = sillaRepo.findAll();

        System.out.print(sillas);
    }
}
