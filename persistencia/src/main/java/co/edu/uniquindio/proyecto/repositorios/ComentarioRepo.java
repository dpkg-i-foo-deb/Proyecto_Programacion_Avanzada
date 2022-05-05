package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.dto.Usuario_Comentarios_DTO;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepo extends JpaRepository<Comentario, Integer> {
    @Query("select new co.edu.uniquindio.proyecto.dto.Usuario_Comentarios_DTO(u.email, c) from Persona_Usuario u left join u.listaComentarios c")
    List<Usuario_Comentarios_DTO> obtenerComentariosUsuario();


    List<Comentario> findByCodigoHotel(Hotel hotel);
}
