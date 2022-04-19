package co.edu.uniquindio.proyecto.entidades.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Email {
    private String nombreCompleto;
    private String email;
    private String contrasena;
    private Date fechaSolicitud;
}
