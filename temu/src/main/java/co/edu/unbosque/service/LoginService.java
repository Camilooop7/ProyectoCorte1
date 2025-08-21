package co.edu.unbosque.service;

import co.edu.unbosque.model.PersonaDTO;
import co.edu.unbosque.model.persistence.PersonaDAO;

import jakarta.enterprise.context.ApplicationScoped;

/**
 * Servicio para gestionar el inicio de sesión de usuarios.
 */
@ApplicationScoped
public class LoginService {

	/** Instancia de PersonaDAO para interactuar con la persistencia. */
	private final PersonaDAO personaDAO;

	/**
	 * Constructor por defecto.
	 */
	public LoginService() {
		this.personaDAO = new PersonaDAO();
	}

	/**
	 * Busca un usuario por correo y contraseña.
	 * 
	 * @param correo     Correo del usuario.
	 * @param contrasena Contraseña del usuario.
	 * @return DTO del usuario si existe, null en caso contrario.
	 */
	public PersonaDTO encontrarPorCorreoYContrasena(String correo, String contrasena) {
		return personaDAO.findC(correo, contrasena);
	}

	/**
	 * Crea un nuevo usuario.
	 * 
	 * @param nuevo DTO del usuario a crear.
	 * @return true si se creó correctamente, false en caso contrario.
	 */
	public boolean crear(PersonaDTO nuevo) {
		return personaDAO.add(nuevo);
	}
}
