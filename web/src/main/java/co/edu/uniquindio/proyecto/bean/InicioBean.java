package co.edu.uniquindio.proyecto.bean;
import org.springframework.stereotype.Component;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
@Component
@ViewScoped
public class InicioBean implements Serializable {
    private String mensaje = "Mi primera página en JSF";
    public String getMensaje() {
        return mensaje;

    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}