package co.edu.unbosque.beans;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.IOException;

import jakarta.enterprise.context.RequestScoped;

/**
 * Bean para gestionar la sesión del usuario y acciones relacionadas.
 */
@Named(value = "userBean")
@RequestScoped
public class UserBean {

	/**
	 * Verifica si hay una sesión activa y redirige al login si no la hay.
	 */
	public void checkSession() {
		String usuario = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		if (usuario == null) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Cierra la sesión actual y redirige al login.
	 */
	public void cerrarSesion() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ec.invalidateSession();
			ec.redirect(ec.getRequestContextPath() + "/index.xhtml");
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al redirigir"));
		}
	}

	/**
	 * Redirige a la página principal.
	 */
	public void mostrarAdmin() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("principal.xhtml");
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al redirigir"));
		}
	}

	/**
	 * Muestra un mensaje de información.
	 */
	public void mostrarInfo() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Mostrando información..."));
	}

	/**
	 * Simula una compra exitosa.
	 */
	public void comprar() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Compra realizada con éxito"));
	}

	/**
	 * Obtiene el script para aplicar el tema.
	 * 
	 * @return Script para aplicar el tema.
	 */
	public String getApplyThemeScript() {
		return "function applyThemeByInputId(id){var el=document.getElementById(id);if(!el)return;document.documentElement.setAttribute('data-theme',el.value);document.body.setAttribute('data-theme',el.value);}";
	}
}
