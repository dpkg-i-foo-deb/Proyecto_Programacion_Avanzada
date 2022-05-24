package co.edu.uniquindio.proyecto.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Mensaje {
    public static void mostrarMensaje(FacesMessage.Severity nivel, String titulo, String texto) {
        FacesMessage mensaje = new FacesMessage(nivel, titulo, texto);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
    }
}
