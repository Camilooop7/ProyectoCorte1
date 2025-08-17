package co.edu.unbosque.service;


import co.edu.unbosque.model.ModelFacade;
import co.edu.unbosque.model.PersonaDTO;
import co.edu.unbosque.model.persistence.FileManager;

public class LoginService {
	
	public LoginService() {
		FileManager.crearCarpeta();
		// TODO Auto-generated constructor stub
	}

	public void crear(PersonaDTO nuevo) {

		ModelFacade.personaDAO.add(nuevo);

	}

	  public boolean encontrar(PersonaDTO dto) {
	        PersonaDTO encontrado = ModelFacade.personaDAO.findC(dto.getCorreo(), dto.getContrasena());
	        return encontrado != null; // true si existe, false si no
	    }
}
