package co.edu.uniquindio.proyecto.bean.administrador;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Departamento;
import co.edu.uniquindio.proyecto.entidades.Persona_Administrador;
import co.edu.uniquindio.proyecto.servicios.IAdministradorServicio;
import co.edu.uniquindio.proyecto.servicios.ICiudadServicio;
import co.edu.uniquindio.proyecto.servicios.IDepartamentoServicio;
import co.edu.uniquindio.proyecto.servicios.excepciones.AdministradorException;
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
public class SignUpAdminBean implements Serializable {

    private final IAdministradorServicio administradorServicio;

    private final IDepartamentoServicio departamentoServicio;

    private final ICiudadServicio ciudadServicio;

    @Getter @Setter
    private Persona_Administrador administrador;

    @Getter @Setter
    private Ciudad ciudad;
    @Getter @Setter
    private List<SelectItem> ciudadesGrupo;

    public SignUpAdminBean(IAdministradorServicio administradorServicio, IDepartamentoServicio departamentoServicio, ICiudadServicio ciudadServicio) {
        this.administradorServicio = administradorServicio;
        this.departamentoServicio = departamentoServicio;
        this.ciudadServicio = ciudadServicio;
    }

    @PostConstruct
    public void init() {
        //Creating a new admin.
        administrador = new Persona_Administrador();

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

    public void registrarAdministrador() {
        try {
            administradorServicio.registrarAdministrador(administrador);

            Mensaje.mostrarMensaje(
                    FacesMessage.SEVERITY_INFO,
                    "Registro exitoso",
                    "Bienvenido a unitravel. Ya puedes iniciar sesión"
            );
        } catch (AdministradorException e) {
            System.err.println("ERROR: " + e.getMessage());

            Mensaje.mostrarMensaje(
                    FacesMessage.SEVERITY_ERROR,
                    "Error en el registro",
                    e.getMessage()
            );
        }
    }

}
