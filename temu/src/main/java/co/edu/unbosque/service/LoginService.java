package co.edu.unbosque.service;

import co.edu.unbosque.model.PersonaDTO;
import co.edu.unbosque.model.persistence.PersonaDAO;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LoginService {

    private final PersonaDAO personaDAO;

    public LoginService() {
        this.personaDAO = new PersonaDAO();
    }

    /**
     * Busca por correo y contraseña usando el DAO real (persistencia).
     */
    public PersonaDTO encontrarPorCorreoYContrasena(String correo, String contrasena) {
        return personaDAO.findC(correo, contrasena);
    }

    /**
     * Crea usuario si no existe (según PersonaDAO.add).
     */
    public boolean crear(PersonaDTO nuevo) {
        return personaDAO.add(nuevo);
    }
}
