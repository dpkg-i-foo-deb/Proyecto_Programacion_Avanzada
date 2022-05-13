package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.entidades.Silla;
import co.edu.uniquindio.proyecto.repositorios.SillaRepo;
import co.edu.uniquindio.proyecto.servicios.ISillaServicio;
import co.edu.uniquindio.proyecto.servicios.excepciones.SillaException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SillaServicioImpl implements ISillaServicio
{
    private final SillaRepo sillaRepo;

    public SillaServicioImpl(SillaRepo sillaRepo) { this.sillaRepo = sillaRepo; }

    @Override
    public Silla crearSilla(Silla silla)
    {
        return sillaRepo.save(silla);
    }

    @Override
    public Silla editarSilla(Silla silla) throws SillaException
    {
        if(sillaRepo.findById(silla.getCodigoSilla()).orElse(null)==null)
            throw new SillaException("La silla no existe");

        return sillaRepo.save(silla);
    }

    @Override
    public List<Silla> obtenerSillas()
    {
        return sillaRepo.findAll();
    }

    @Override
    public Silla obtenerSilla(Integer codigo) throws SillaException
    {
        Silla silla = sillaRepo.findById(codigo).orElse(null);

        if(silla == null)
            throw new SillaException("La silla no existe");

        return silla;
    }

    @Override
    public boolean eliminarSilla(Silla silla)
    {
        try {
            sillaRepo.delete(silla);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
