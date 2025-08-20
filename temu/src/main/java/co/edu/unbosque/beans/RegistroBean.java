package co.edu.unbosque.beans;

import co.edu.unbosque.model.PersonaDTO;
import co.edu.unbosque.service.RegistroService;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Named("registroBean")
@SessionScoped
public class RegistroBean implements Serializable {

    private static final long serialVersionUID = 1L;

    // Campos de Persona (segunda página)
    private String genero;
    private String direccion;
    private Integer edad;
    private Integer identificacion;

    @Inject
    private LoginBean loginBean; // Para tomar nombre/correoC/contrasenaC del primer paso

    @Inject
    private RegistroService registroService;

    /**
     * Guarda el usuario completo y redirige al Login.
     */
    public String guardar() {
        try {
            if (loginBean.getCorreoC() == null || loginBean.getCorreoC().isBlank() ||
                loginBean.getContrasenaC() == null || loginBean.getContrasenaC().isBlank() ||
                loginBean.getNombre() == null || loginBean.getNombre().isBlank()) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registro", "Faltan datos del primer paso (Login → Sign Up)."));
                return null;
            }

            PersonaDTO dto = new PersonaDTO(
                loginBean.getNombre(),
                genero != null ? genero : "",
                direccion != null ? direccion : "",
                edad != null ? edad : 0,
                identificacion != null ? identificacion : 0,
                null, // carrito
                null  // lista favoritos
            );
            // completar credenciales
            dto.setCorreo(loginBean.getCorreoC());
            dto.setContrasena(loginBean.getContrasenaC());
            // si manejas username distinto, podrías derivarlo del correo:
            dto.setUsername(loginBean.getCorreoC());

            boolean creado = registroService.crearUsuarioTotal(dto);
            if (!creado) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Registro", "Ya existe un usuario con ese username/correo."));
                return null;
            }

            // Limpio datos de registro del paso 2 (opcional)
            limpiarLocales();

            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Usuario creado. Ahora inicia sesión."));
            // Vuelve al Login
            return "index.xhtml";

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo completar el registro."));
            return null;
        }
    }

    private void limpiarLocales() {
        genero = null;
        direccion = null;
        edad = null;
        identificacion = null;
    }

    // Getters/Setters
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public Integer getEdad() { return edad; }
    public void setEdad(Integer edad) { this.edad = edad; }
    public Integer getIdentificacion() { return identificacion; }
    public void setIdentificacion(Integer identificacion) { this.identificacion = identificacion; }
}
