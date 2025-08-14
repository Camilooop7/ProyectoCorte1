package co.edu.unbosque.service;

import co.edu.unbosque.model.ModelFacade;
import co.edu.unbosque.model.Persona;
import co.edu.unbosque.model.PersonaDTO;
import co.edu.unbosque.model.persistence.PersonaDAO;
import jakarta.enterprise.inject.Model;

public class LoginService {
	public LoginService() {
		// TODO Auto-generated constructor stub
	}

	public void crear(PersonaDTO nuevo) {
		ModelFacade.personaDAO.add(nuevo);

	}

	public void encontrar(Persona encontrar) {
		ModelFacade.personaDAO.find(encontrar);

	}
}
