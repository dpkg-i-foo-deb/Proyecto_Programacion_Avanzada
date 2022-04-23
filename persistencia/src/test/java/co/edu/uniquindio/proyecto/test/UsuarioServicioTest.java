package co.edu.uniquindio.proyecto.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class UsuarioServicioTest {

    @Autowired
    private IUsuarioServicio iUsuarioServicio;


}
