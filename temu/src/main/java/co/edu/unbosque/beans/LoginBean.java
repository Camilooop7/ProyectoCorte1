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
		        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

		        // Nombre/alias visible
		        String valorSesion = (encontrado.getUsername() != null && !encontrado.getUsername().isBlank())
		                ? encontrado.getUsername()
		                : encontrado.getCorreo();
		        ec.getSessionMap().put("usuario", valorSesion);

		        // === CLAVE: guarda el correo autenticado ===
		        ec.getSessionMap().put("correo", encontrado.getCorreo());

		        // (Opcional) si tu PersonaDTO tiene dirección:
		        try {
		            String dir = (String) PersonaDTO.class.getMethod("getDireccion").invoke(encontrado);
		            if (dir != null && !dir.isBlank()) {
		                ec.getSessionMap().put("direccion", dir);
		            }
		        } catch (Exception ignored) {
		            // Si no existe getDireccion o falla, lo ignoramos.
		        }

		        // Navegación
		        return "userpp.xhtml"; // ajusta si tu ruta es distinta
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
		    try {
		        // Validaciones con tus excepciones personalizadas
		        ExceptionCheker.checkerText(nombre);
		        ExceptionCheker.checkerMail(correoC);
		        ExceptionCheker.checkerCharacter(contrasenaC);

		        // Validaciones originales
		        if (correoC == null || correoC.isBlank() ||
		            contrasenaC == null || contrasenaC.isBlank() ||
		            nombre == null || nombre.isBlank()) {
		            FacesContext.getCurrentInstance().addMessage(null,
		                new FacesMessage(FacesMessage.SEVERITY_WARN,
		                    "Campos incompletos", "Completa nombre, correo y contraseña."));
		            return null; // detener flujo
		        }
		        if (!contrasenaC.equals(confiContrasenaC)) {
		            FacesContext.getCurrentInstance().addMessage(null,
		                new FacesMessage(FacesMessage.SEVERITY_ERROR,
		                    "Contraseña", "Las contraseñas no coinciden."));
		            return null; // detener flujo
		        }

		    } catch (TextException e) {
		        FacesContext.getCurrentInstance().addMessage(null,
		            new FacesMessage(FacesMessage.SEVERITY_ERROR,
		                "nombre con simbolos", "no puede tener símbolos."));
		        return null; // detener flujo aquí
		    } catch (MailException e) {
		        FacesContext.getCurrentInstance().addMessage(null,
		            new FacesMessage(FacesMessage.SEVERITY_ERROR,
		                "formato correo", "formato correo invalido."));
		        return null; // detener flujo aquí
		    } catch (CharacterException e) {
		        FacesContext.getCurrentInstance().addMessage(null,
			            new FacesMessage(FacesMessage.SEVERITY_ERROR,
			                "minimo 8 caracteres en la contraseña", "longitud de contraseña invalida"));
			        return null; // detener flujo aquí
			    }

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
		                "No se pudo enviar el correo de confirmación: " +
		                (e.getCause() != null ? e.getCause().getMessage() : e.getMessage())));
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
