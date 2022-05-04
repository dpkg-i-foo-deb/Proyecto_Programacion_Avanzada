package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.entidades.Departamento;
import co.edu.uniquindio.proyecto.repositorios.DepartamentoRepo;
import co.edu.uniquindio.proyecto.servicios.IDepartamentoServicio;
import co.edu.uniquindio.proyecto.servicios.excepciones.DepartamentoException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartamentoServicioImpl implements IDepartamentoServicio
{

    private final DepartamentoRepo departamentoRepo;

    public DepartamentoServicioImpl(DepartamentoRepo departamentoRepo)
    {
        this.departamentoRepo = departamentoRepo;
    }

    @Override
    public Departamento registrarDepartamento(Departamento departamento)
    {
        return departamentoRepo.save(departamento);
    }

    @Override
    public List<Departamento> obtenerDepartamentos()
    {
        return departamentoRepo.findAll();
    }

    @Override
    public Departamento obtenerDepartamento(Integer codigo)  throws DepartamentoException
    {
        Departamento departamento;

        departamento = departamentoRepo.findById(codigo).orElse(null);

        if(departamento==null)
            throw new DepartamentoException("El departamento no existe");

        return departamento;
    }

    @Override
    public Departamento editarDepartamento(Departamento departamento) throws DepartamentoException
    {
       if(departamentoRepo.findById(departamento.getCodigoDepartamento()).orElse(null)==null)
            throw new DepartamentoException("El departamento no existe");

        return departamentoRepo.save(departamento);
    }

    @Override
    public void eliminarDepartamento(Departamento departamento)
    {
        departamentoRepo.delete(departamento);
    }
}
