package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.dto.Ciudad_TotalHoteles_DTO;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Departamento;
import co.edu.uniquindio.proyecto.entidades.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CiudadRepo extends JpaRepository<Ciudad, Integer>
{
    @Query("select c.listaHoteles from Ciudad c where c.nombre = :nombreCiudad")
    List<Hotel> obtenerHoteles(String nombreCiudad);

    @Query("select new co.edu.uniquindio.proyecto.dto.Ciudad_TotalHoteles_DTO(c, count(h)) from Ciudad c left join c.listaHoteles h group by c")
    List<Ciudad_TotalHoteles_DTO> obtenerTotalHotelesPorCiudad();

    List<Ciudad> findAllByDepartamento(Departamento departamento);
}
