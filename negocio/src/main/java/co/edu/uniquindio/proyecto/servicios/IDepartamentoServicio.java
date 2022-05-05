package co.edu.uniquindio.proyecto.servicios;


import co.edu.uniquindio.proyecto.entidades.Departamento;
import co.edu.uniquindio.proyecto.servicios.excepciones.DepartamentoException;

import java.util.List;

public interface IDepartamentoServicio
{
    Departamento registrarDepartamento(Departamento departamento);
    List<Departamento> obtenerDepartamentos();

    Departamento obtenerDepartamento(Integer codigo) throws DepartamentoException;

    Departamento editarDepartamento(Departamento departamento) throws DepartamentoException;

    void eliminarDepartamento(Departamento departamento);
}
