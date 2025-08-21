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
	public String guardar() throws NumberException {
	    try {
	        // ===== Paso 1 (de login): datos mínimos =====
	        if (loginBean == null ||
	            loginBean.getCorreoC() == null || loginBean.getCorreoC().isBlank() ||
	            loginBean.getContrasenaC() == null || loginBean.getContrasenaC().isBlank() ||
	            loginBean.getNombre() == null || loginBean.getNombre().isBlank()) {

	            FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Registro", "Faltan datos del primer paso (Login → Sign Up)."));
	            return null; // cortar flujo
	        }

	       

	        try {
	            ExceptionCheker.checkerIsBlank(direccion);
	        } catch (IsBlackException ex) {
	            FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Dirección", "La dirección es obligatoria."));
	            return null; // cortar flujo
	        }

	        try {
	            ExceptionCheker.checkerIsBlank(genero);
	        } catch (IsBlackException ex) {
	            FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Género", "El género es obligatorio."));
	            return null; // cortar flujo
	        }

	        // ===== Si todo es válido, construir DTO =====
	        PersonaDTO dto = new PersonaDTO(
	            loginBean.getNombre(),
	            genero != null ? genero : "",
	            direccion != null ? direccion : "",
	            edad != null ? edad : 0,
	            identificacion != null ? identificacion : 0,
	            null, // carrito
	            null  // lista de favoritos
	        );

	        // Completar credenciales
	        dto.setCorreo(loginBean.getCorreoC());
	        dto.setContrasena(loginBean.getContrasenaC());
	        dto.setUsername(loginBean.getCorreoC()); // o deriva como prefieras

	        // ===== Persistencia =====
	        boolean creado = registroService.crearUsuarioTotal(dto);
	        if (!creado) {
	            FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage(FacesMessage.SEVERITY_WARN,
	                    "Registro", "Ya existe un usuario con ese username/correo."));
	            return null; // no continuar navegación
	        }

	        // Limpio datos de registro del paso 2 (opcional)
	        limpiarLocales();

	        FacesContext.getCurrentInstance().addMessage(null,
	            new FacesMessage(FacesMessage.SEVERITY_INFO,
	                "Registro", "Usuario creado. Ahora inicia sesión."));
	        return "index.xhtml";

	    } catch (Exception e) {
	        // Cualquier error no previsto
	        FacesContext.getCurrentInstance().addMessage(null,
	            new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                "Registro",
	                "Verifique los datos ingresados. No se puede completar el registro."));
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
