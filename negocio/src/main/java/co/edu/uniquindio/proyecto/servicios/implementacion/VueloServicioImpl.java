package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Vuelo;
import co.edu.uniquindio.proyecto.repositorios.VueloRepo;
import co.edu.uniquindio.proyecto.servicios.IVueloServicio;
import co.edu.uniquindio.proyecto.servicios.excepciones.VueloException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VueloServicioImpl implements IVueloServicio
{
    private final VueloRepo vueloRepo;

    public VueloServicioImpl(VueloRepo vueloRepo) { this.vueloRepo = vueloRepo; }

    @Override
    public Vuelo crearVuelo(Vuelo vuelo) throws VueloException
    {
        Ciudad ciudadOrigen = vuelo.getCiudadOrigen();
        Ciudad ciudadDestino = vuelo.getCiudadDestino();

        if(ciudadOrigen.equals(ciudadDestino))
            throw new VueloException("La ciudad origen y destino deben ser diferentes");

        return vueloRepo.save(vuelo);
    }

    @Override
    public List<Vuelo> obtenerVuelos()
    {
        return vueloRepo.findAll();
    }

    @Override
    public Vuelo obtenerVuelo(Integer codigo) throws VueloException
    {
        Vuelo vuelo = vueloRepo.findById(codigo).orElse(null);

        if(vuelo==null)
            throw new VueloException("El vuelo no existe");

        return vuelo;
    }

    @Override
    public Vuelo editarVuelo(Vuelo vuelo) throws VueloException
    {
        if(vueloRepo.findById(vuelo.getCodigoVuelo()).orElse(null)==null)
            throw new VueloException("El vuelo no existe");

       return vueloRepo.save(vuelo);
    }

    @Override
    public boolean eliminarVuelo(Vuelo vuelo)
    {
        vueloRepo.delete(vuelo);

        return true;
    }
}
