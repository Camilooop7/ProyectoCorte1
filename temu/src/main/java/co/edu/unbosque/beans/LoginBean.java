package co.edu.unbosque.beans;

import co.edu.unbosque.model.PersonaDTO;
import co.edu.unbosque.service.LoginService;
import co.edu.unbosque.util.exception.CharacterException;
import co.edu.unbosque.util.exception.ExceptionCheker;
import co.edu.unbosque.util.exception.MailException;
import co.edu.unbosque.util.exception.TextException;
import co.edu.unbosque.util.mail.EnvioCorreo;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;

/**
 * Bean para gestionar el inicio de sesión y registro de usuarios.
 */
@Named("loginbean")
@SessionScoped
public class LoginBean implements Serializable {

	/** Versión para la serialización. */
	private static final long serialVersionUID = 1L;

	private String correo;
	private String contrasena;

	private String nombre;
	private String correoC;
	private String contrasenaC;
	private String confiContrasenaC;

	/** Servicio de login. */
	@Inject
	private LoginService loginService;

	/** Servicio de envío de correos. */
	@Inject
	private EnvioCorreo envioCorreo;

	// Tema actual
	private String theme = "dark";

	/**
	 * Inicia sesión con el correo y contraseña proporcionados.
	 * 
	 * @return Ruta de redirección.
	 */
	public String iniciar() {
		PersonaDTO encontrado = loginService.encontrarPorCorreoYContrasena(correo, contrasena);
		if (encontrado != null) {
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			String valorSesion = (encontrado.getUsername() != null && !encontrado.getUsername().isBlank())
					? encontrado.getUsername()
					: encontrado.getCorreo();
			ec.getSessionMap().put("usuario", valorSesion);
			ec.getSessionMap().put("correo", encontrado.getCorreo());
			try {
				String dir = (String) PersonaDTO.class.getMethod("getDireccion").invoke(encontrado);
				if (dir != null && !dir.isBlank()) {
					ec.getSessionMap().put("direccion", dir);
				}
			} catch (Exception ignored) {
			}
			return "userpp.xhtml";
		}
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Correo o contraseña inválidos"));
		return null;
	}

	/**
	 * Crea un nuevo usuario y envía un correo de confirmación.
	 * 
	 * @return Ruta de redirección.
	 */
	public String crear() {
		try {
			ExceptionCheker.checkerText(nombre);
			ExceptionCheker.checkerMail(correoC);
			ExceptionCheker.checkerCharacter(contrasenaC);
			if (correoC == null || correoC.isBlank() || contrasenaC == null || contrasenaC.isBlank() || nombre == null
					|| nombre.isBlank()) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Campos incompletos", "Completa nombre, correo y contraseña."));
				return null;
			}
			if (!contrasenaC.equals(confiContrasenaC)) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Contraseña", "Las contraseñas no coinciden."));
				return null;
			}
		} catch (TextException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "nombre con simbolos", "no puede tener símbolos."));
			return null;
		} catch (MailException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "formato correo", "formato correo invalido."));
			return null;
		} catch (CharacterException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"minimo 8 caracteres en la contraseña", "longitud de contraseña invalida"));
			return null;
		}
		try {
			if (envioCorreo == null) {
				envioCorreo = jakarta.enterprise.inject.spi.CDI.current()
						.select(co.edu.unbosque.util.mail.EnvioCorreo.class).get();
			}
			String asunto = "Confirmación de cuenta - TEMU";
			String cuerpoHtml = "<div style='font-family:Arial,sans-serif'>" + "  <h2>¡Hola, "
					+ (nombre != null ? nombre : "usuario") + "!</h2>"
					+ "  <p>Tu cuenta en <b>TEMU</b> ha sido registrada correctamente.</p>" + "  <hr/>" + "  <small>© "
					+ java.time.Year.now() + " TEMU</small>" + "</div>";
			envioCorreo.createEmail(correoC, asunto, cuerpoHtml);
			envioCorreo.sendEmail();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Registro exitoso", "Te enviamos un correo de confirmación a " + correoC));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Registro realizado",
							"No se pudo enviar el correo de confirmación: "
									+ (e.getCause() != null ? e.getCause().getMessage() : e.getMessage())));
		}
		return "registro.xhtml";
	}

	/**
	 * Cambia el tema entre claro y oscuro.
	 */
	public void toggleTheme() {
		theme = "dark".equals(theme) ? "light" : "dark";
	}

	/**
	 * Obtiene el ícono del tema actual.
	 * 
	 * @return Ícono del tema.
	 */
	public String getThemeIcon() {
		return "dark".equals(theme) ? "brightness_4" : "brightness_7";
	}

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

	public String getTheme() {
		return theme;
	}

	public String getApplyThemeScript() {
		return "function applyThemeByInputId(id){" + "var el=document.getElementById(id);" + "if(!el)return;"
				+ "document.documentElement.setAttribute('data-theme',el.value);"
				+ "document.body.setAttribute('data-theme',el.value);" + "}";
	}
}
