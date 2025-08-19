package co.edu.unbosque.service;

import co.edu.unbosque.model.AudifonoDTO;
import co.edu.unbosque.model.ColegioDTO;
import co.edu.unbosque.model.HombreDTO;
import co.edu.unbosque.model.JuegoMesaDTO;
import co.edu.unbosque.model.LabialDTO;
import co.edu.unbosque.model.ModelFacade;
import co.edu.unbosque.model.MovilDTO;
import co.edu.unbosque.model.MujerDTO;
import co.edu.unbosque.model.OficinaDTO;
import co.edu.unbosque.model.PestaninaDTO;

public class PrincipalService {
	
	public PrincipalService() {
		// TODO Auto-generated constructor stub
	}
	
	 public void crearAu(AudifonoDTO eliminar) {
	        ModelFacade.audifonoDAO.delete(eliminar);
	    }

	    public void crearMo(MovilDTO eliminar) {
	        ModelFacade.movilDAO.delete(eliminar);
	    }

	    public void crearLa(LabialDTO eliminar) {
	        ModelFacade.labialDAO.delete(eliminar);
	    }

	    public void crearPes(PestaninaDTO eliminar) {
	        ModelFacade.pestaninaDAO.delete(eliminar);
	    }

	    public void crearJue(JuegoMesaDTO eliminar) {
	        ModelFacade.juegoMesaDAO.delete(eliminar);
	    }

	    public void crearCo(ColegioDTO eliminar) {
	        ModelFacade.colegioDAO.delete(eliminar);
	    }

	    public void crearOfi(OficinaDTO eliminar) {
	        ModelFacade.oficinaDAO.delete(eliminar);
	    }

	    public void crearHom(HombreDTO eliminar) {
	        ModelFacade.hombreDAO.add(eliminar);
	    }

	    public void crearMuj(MujerDTO eliminar) {
	        ModelFacade.mujerDAO.add(eliminar);
	    }

}
