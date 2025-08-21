package co.edu.unbosque.beans;

import co.edu.unbosque.model.PersonaDTO;
import co.edu.unbosque.service.RegistroService;
import co.edu.unbosque.util.exception.ExceptionCheker;
import co.edu.unbosque.util.exception.IsBlackException;
import co.edu.unbosque.util.exception.NumberException;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

/**
 * Bean para gestionar el registro de usuarios.
 */
@Named("registroBean")
@SessionScoped
public class RegistroBean implements Serializable {

	/** Versión para la serialización. */
	private static final long serialVersionUID = 1L;

	// Campos de registro
	private String genero;
	private String direccion;
	private Integer edad;
	private Integer identificacion;

	/** Bean de login para obtener datos del primer paso. */
	@Inject
	private LoginBean loginBean;

	/** Servicio de registro. */
	@Inject
	private RegistroService registroService;

	/**
	 * Guarda el usuario completo y redirige al login.
	 * 
	 * @return Ruta de redirección.
	 * @throws NumberException Si hay un error con los números.
	 */
	public String guardar() throws NumberException {
		try {
			if (loginBean == null || loginBean.getCorreoC() == null || loginBean.getCorreoC().isBlank()
					|| loginBean.getContrasenaC() == null || loginBean.getContrasenaC().isBlank()
					|| loginBean.getNombre() == null || loginBean.getNombre().isBlank()) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Registro", "Faltan datos del primer paso (Login → Sign Up)."));
				return null;
			}
			try {
				ExceptionCheker.checkerIsBlank(direccion);
			} catch (IsBlackException ex) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Dirección", "La dirección es obligatoria."));
				return null;
			}
			try {
				ExceptionCheker.checkerIsBlank(genero);
			} catch (IsBlackException ex) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Género", "El género es obligatorio."));
				return null;
			}
			PersonaDTO dto = new PersonaDTO(loginBean.getNombre(), genero != null ? genero : "",
					direccion != null ? direccion : "", edad != null ? edad : 0,
					identificacion != null ? identificacion : 0, null, // carrito
					null // lista de favoritos
			);
			dto.setCorreo(loginBean.getCorreoC());
			dto.setContrasena(loginBean.getContrasenaC());
			dto.setUsername(loginBean.getCorreoC());
			boolean creado = registroService.crearUsuarioTotal(dto);
			if (!creado) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Registro", "Ya existe un usuario con ese username/correo."));
				return null;
			}
			limpiarLocales();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Usuario creado. Ahora inicia sesión."));
			return "index.xhtml";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registro",
					"Verifique los datos ingresados. No se puede completar el registro."));
			return null;
		}
	}

	/**
	 * Limpia los campos locales.
	 */
	private void limpiarLocales() {
		genero = null;
		direccion = null;
		edad = null;
		identificacion = null;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public Integer getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(Integer identificacion) {
		this.identificacion = identificacion;
	}
}
