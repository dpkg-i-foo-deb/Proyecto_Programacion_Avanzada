package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.servicios.ICiudadServicio;
import co.edu.uniquindio.proyecto.servicios.excepciones.CiudadException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CiudadServicioImpl implements ICiudadServicio
{
    private final CiudadRepo ciudadRepo;

    public CiudadServicioImpl(CiudadRepo ciudadRepo) { this.ciudadRepo = ciudadRepo; }

    @Override
    public Ciudad registrarCiudad(Ciudad ciudad)
    {
        return ciudadRepo.save(ciudad);
    }

    @Override
    public Ciudad editarCiudad(Ciudad ciudad) throws CiudadException
    {
        if(ciudadRepo.findById(ciudad.getCodigoCiudad()).orElse(null)==null)
            throw new CiudadException("La ciudad no existe");

        return ciudadRepo.save(ciudad);
    }

    @Override
    public void eliminarCiudad(Ciudad ciudad)
    {
        ciudadRepo.delete(ciudad);
    }

    @Override
    public Ciudad obtenerCiudad(Integer codigo) throws CiudadException
    {
        Ciudad ciudad = ciudadRepo.findById(codigo).orElse(null);

        if(ciudad==null)
            throw new CiudadException("La ciudad no existe");

        return ciudad;
    }

    @Override
    public List<Ciudad> obtenerCiudades()
    {
        return ciudadRepo.findAll();
    }
}
