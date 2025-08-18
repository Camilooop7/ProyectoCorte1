package co.edu.unbosque.beans;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import java.io.IOException;

@Named(value = "principalbean")
@RequestScoped
public class PrincipalBean {

    public void agregar() {
        try {
            // Usa la ruta absoluta desde el contexto de la aplicación
            String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + "/add.xhtml");
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo redirigir a la página de agregar producto."));
        }
    }

    public void eliminar() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Producto eliminado con éxito"));
    }

    public void actualizar() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Producto actualizado con éxito"));
    }
}
