package co.edu.uniquindio.proyecto.bean.usuario;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Departamento;
import co.edu.uniquindio.proyecto.entidades.Persona_Usuario;
import co.edu.uniquindio.proyecto.servicios.ICiudadServicio;
import co.edu.uniquindio.proyecto.servicios.IDepartamentoServicio;
import co.edu.uniquindio.proyecto.servicios.IUsuarioServicio;
import co.edu.uniquindio.proyecto.servicios.excepciones.UsuarioException;
import co.edu.uniquindio.proyecto.utils.Mensaje;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class SignUpBean implements Serializable {
    private final IUsuarioServicio usuarioServicio;

    private final IDepartamentoServicio departamentoServicio;

    private final ICiudadServicio ciudadServicio;

    @Getter @Setter
    private Persona_Usuario usuario;

    @Getter @Setter
    private Ciudad ciudad;
    @Getter @Setter
    private List<SelectItem> ciudadesGrupo;

    public SignUpBean(IUsuarioServicio usuarioServicio, IDepartamentoServicio departamentoServicio, ICiudadServicio ciudadServicio) {
        this.usuarioServicio = usuarioServicio;
        this.departamentoServicio = departamentoServicio;
        this.ciudadServicio = ciudadServicio;
    }

    @PostConstruct
    public void init() {
        //Creating a new user.
        usuario = new Persona_Usuario();

        //Populating selector options.
        ciudadesGrupo = new ArrayList<>();

        List<Departamento> departamentos = departamentoServicio.obtenerDepartamentos();

        departamentos.forEach(d -> {
            SelectItemGroup group = new SelectItemGroup(d.getNombre());

            SelectItem[] ciudades = ciudadServicio.obtenerCiudadesDepartamento(d)
                    .stream().map(c -> new SelectItem(c, c.getNombre()))
                    .toArray(SelectItem[]::new);

            group.setSelectItems(ciudades);

            ciudadesGrupo.add(group);
        });
    }

    public void registrarUsuario() {
        try {
            usuarioServicio.registrarUsuario(usuario);

            Mensaje.mostrarMensaje(
                FacesMessage.SEVERITY_INFO,
                "Registro exitoso",
                "Bienvenido a unitravel. Ya puedes iniciar sesión"
            );
        } catch (UsuarioException e) {
            System.err.println("ERROR: " + e.getMessage());

            Mensaje.mostrarMensaje(
                FacesMessage.SEVERITY_ERROR,
                "Error en el registro",
                e.getMessage()
            );
        }
    }
}
