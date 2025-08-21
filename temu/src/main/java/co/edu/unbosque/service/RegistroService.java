package co.edu.unbosque.service;

import co.edu.unbosque.model.PersonaDTO;
import co.edu.unbosque.model.persistence.PersonaDAO;

import jakarta.enterprise.context.ApplicationScoped;

/**
 * Servicio para gestionar el registro de usuarios.
 */
@ApplicationScoped
public class RegistroService {

	/** Instancia de PersonaDAO para interactuar con la persistencia. */
	private final PersonaDAO personaDAO;

	/**
	 * Constructor por defecto.
	 */
	public RegistroService() {
		this.personaDAO = new PersonaDAO();
	}

	/**
	 * Crea un usuario completo en persistencia.
	 * 
	 * @param dto DTO del usuario a crear.
	 * @return true si se creó correctamente, false en caso contrario.
	 */
	public boolean crearUsuarioTotal(PersonaDTO dto) {
		if (dto.getUsername() == null || dto.getUsername().isBlank()) {
			dto.setUsername(dto.getCorreo());
		}
		return personaDAO.add(dto);
	}
}
