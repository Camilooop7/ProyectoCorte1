package co.edu.unbosque.beans;

import co.edu.unbosque.model.PersonaDTO;
import co.edu.unbosque.service.LoginService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import java.io.IOException;

@Named(value = "loginbean")
@RequestScoped
public class LoginBean {
    private String correo;
    private String contrasena;
    private String nombre;
    private String correoC;
    private String contrasenaC;
    private String confiContrasenaC;
    private LoginService loginService;

    public LoginBean() {
        this.loginService = new LoginService();
    }

    // Getters y Setters
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoC() {
        return correoC;
    }

    public void setCorreoC(String correoC) {
        this.correoC = correoC;
    }

    public String getContrasenaC() {
        return contrasenaC;
    }

    public void setContrasenaC(String contrasenaC) {
        this.contrasenaC = contrasenaC;
    }

    public String getConfiContrasenaC() {
        return confiContrasenaC;
    }

    public void setConfiContrasenaC(String confiContrasenaC) {
        this.confiContrasenaC = confiContrasenaC;
    }

    public void iniciar() {
        PersonaDTO dto = new PersonaDTO(nombre, contrasena, correo);
        boolean acceso = loginService.encontrar(dto);
        if (acceso) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", nombre);
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("userpp.xhtml");
            } catch (IOException e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al redirigir"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario o contraseña incorrectos"));
        }
    }

    public void mostrarIniciar() {
        System.out.println("Correo: " + correo);
        System.out.println("Contraseña: " + contrasena);
    }

    public void mostrarCrear() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Correo: " + correoC);
        System.out.println("Contraseña: " + contrasenaC);
        System.out.println("Confirmar Contraseña: " + confiContrasenaC);
    }

    public void crear() {
        if (contrasenaC.equals(confiContrasenaC)) {
            PersonaDTO nuevo = new PersonaDTO(nombre, contrasenaC, correoC);
            loginService.crear(nuevo);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Usuario creado exitosamente"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Las contraseñas no coinciden"));
        }
    }
}
