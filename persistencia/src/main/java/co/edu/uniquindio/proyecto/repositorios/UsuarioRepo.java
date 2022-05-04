package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Hotel;
import co.edu.uniquindio.proyecto.entidades.Persona_Usuario;
import co.edu.uniquindio.proyecto.entidades.Reserva;
import co.edu.uniquindio.proyecto.dto.Usuario_Reservas_DTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepo extends JpaRepository <Persona_Usuario, String>
{
    Persona_Usuario getPersona_UsuarioByCedula(String cedula);

    Optional<Persona_Usuario> findByEmail(String email);

    boolean existsByCedulaOrEmail(String cedula, String email);

    List<Persona_Usuario> findAllByNombreCompleto(String nombre);

    Page<Persona_Usuario> findAll(Pageable pageable);

    @Query("select r from Persona_Usuario u, IN (u.reservas) r where u.email = :email")
    List<Reserva> obtenerReservasByEmail(String email);

    @Query("select new co.edu.uniquindio.proyecto.dto.Usuario_Reservas_DTO(u.email, r) from Persona_Usuario u left join u.reservas r")
    List<Usuario_Reservas_DTO> obtenerReservasUsuario();

    @Query("select distinct u from Persona_Usuario u join u.telefonos t where t = :telefono")
    List<Persona_Usuario> obtenerUsuariosTelefono(String telefono);

    @Query("select h from Persona_Usuario u, in(u.hotelesFavoritos) h where u.email = :email")
    List<Hotel> obtenerHotelesFavoritosUsuario(String email);

    @Query(value = "select h from Persona_Usuario u, in(u.hotelesFavoritos) h where u.email = :email and lower(h.nombre) like concat('%', lower(:nombreHotel), '%')")
    List<Hotel> obtenerHotelesFavoritosByName(String email, String nombreHotel);
}
