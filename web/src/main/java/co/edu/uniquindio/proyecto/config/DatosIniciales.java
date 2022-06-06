package co.edu.uniquindio.proyecto.config;

import co.edu.uniquindio.proyecto.servicios.implementacion.AdministradorHotelServicioImpl;
import co.edu.uniquindio.proyecto.servicios.implementacion.AdministradorServicioImpl;
import co.edu.uniquindio.proyecto.servicios.implementacion.CiudadServicioImpl;
import co.edu.uniquindio.proyecto.servicios.implementacion.UsuarioServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatosIniciales implements CommandLineRunner{

    @Autowired
    private AdministradorServicioImpl administradorServicio;

    @Autowired
    private CiudadServicioImpl ciudadServicio;

    @Autowired
    private AdministradorHotelServicioImpl administradorHotelServicio;

    @Autowired
    private UsuarioServicioImpl usuarioServicio;

    public void run(String... args) throws Exception{


    }

}
