package co.edu.unbosque.service;

import co.edu.unbosque.model.PersonaDTO;
import co.edu.unbosque.model.persistence.PersonaDAO;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RegistroService {

    private final PersonaDAO personaDAO;

    public RegistroService() {
        this.personaDAO = new PersonaDAO();
    }

    /**
     * Crea el usuario completo en persistencia.
     * Regresa false si ya existe (según equals por username en tu DAO).
     */
    public boolean crearUsuarioTotal(PersonaDTO dto) {
        // Regla de negocio: si username está vacío, usar el correo
        if (dto.getUsername() == null || dto.getUsername().isBlank()) {
            dto.setUsername(dto.getCorreo());
        }
        return personaDAO.add(dto);
    }
}
