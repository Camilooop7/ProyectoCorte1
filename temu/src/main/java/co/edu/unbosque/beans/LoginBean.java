package co.edu.unbosque.beans;

import co.edu.unbosque.model.PersonaDTO;
import co.edu.unbosque.service.LoginService;
import co.edu.unbosque.util.mail.EnvioCorreo;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;

@Named("loginbean")
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// Login (frente)
	private String correo;
	private String contrasena;

	// Sign Up (dorso)
	private String nombre;
	private String correoC;
	private String contrasenaC;
	private String confiContrasenaC;

	@Inject
	private LoginService loginService;
	
	 @Inject
	    private EnvioCorreo envioCorreo;

	public String iniciar() {
	    PersonaDTO encontrado = loginService.encontrarPorCorreoYContrasena(correo, contrasena);

	    if (encontrado != null) {
	        // Guarda el usuario en la sesión para que checkSession lo reconozca
	        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	        String valorSesion = (encontrado.getUsername() != null && !encontrado.getUsername().isBlank())
	                ? encontrado.getUsername()
	                : encontrado.getCorreo();
	        ec.getSessionMap().put("usuario", valorSesion);

	        // Navegación con redirect para evitar reenvíos del mismo request
	        return "/userpp.xhtml"; // ajusta la ruta si está en carpeta
	    }

	    FacesContext.getCurrentInstance().addMessage(null,
	            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Correo o contraseña inválidos"));
	    return null;
	}

	/**
	 * Primer paso del registro (desde el dorso de la tarjeta en Login). Guarda
	 * nombre/correo/contraseña en sesión y navega a registroPersona.xhtml
	 */
	public String crear() {
	    // Validaciones originales
	    if (correoC == null || correoC.isBlank() ||
	        contrasenaC == null || contrasenaC.isBlank() ||
	        nombre == null || nombre.isBlank()) {
	        FacesContext.getCurrentInstance().addMessage(null,
	            new FacesMessage(FacesMessage.SEVERITY_WARN,
	                "Campos incompletos", "Completa nombre, correo y contraseña."));
	        return null;
	    }
	    if (!contrasenaC.equals(confiContrasenaC)) {
	        FacesContext.getCurrentInstance().addMessage(null,
	            new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                "Contraseña", "Las contraseñas no coinciden."));
	        return null;
	    }

	    // (Tu persistencia si aplica)
	    // usuarioService.crear(nombre, correoC, contrasenaC);

	    // Asegurar que envioCorreo no sea null (fallback CDI si hiciera falta)
	    try {
	        if (envioCorreo == null) {
	            envioCorreo = jakarta.enterprise.inject.spi.CDI.current()
	                .select(co.edu.unbosque.util.mail.EnvioCorreo.class).get();
	        }

	        String asunto = "Confirmación de cuenta - TEMU";
	        String cuerpoHtml =
	            "<div style='font-family:Arial,sans-serif'>"
	          + "  <h2>¡Hola, " + (nombre != null ? nombre : "usuario") + "!</h2>"
	          + "  <p>Tu cuenta en <b>TEMU</b> ha sido registrada correctamente.</p>"
	          + "  <hr/>"
	          + "  <small>© " + java.time.Year.now() + " TEMU</small>"
	          + "</div>";

	        envioCorreo.createEmail(correoC, asunto, cuerpoHtml);
	        envioCorreo.sendEmail();

	        FacesContext.getCurrentInstance().addMessage(null,
	            new FacesMessage(FacesMessage.SEVERITY_INFO,
	                "Registro exitoso",
	                "Te enviamos un correo de confirmación a " + correoC));
	    } catch (Exception e) {
	        // Captura tanto NPE por inyección como MessagingException
	        FacesContext.getCurrentInstance().addMessage(null,
	            new FacesMessage(FacesMessage.SEVERITY_WARN,
	                "Registro realizado",
	                "No se pudo enviar el correo de confirmación: " + (e.getCause() != null ? e.getCause().getMessage() : e.getMessage())));
	    }

	    // Mantener tu navegación
	    return "registro.xhtml";
	}



	// Getters/Setters
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
}
