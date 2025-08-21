package co.edu.unbosque.service;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import co.edu.unbosque.model.ModelFacade;

// ENTIDADES para la UI



// DTOs (capa de persistencia)
import co.edu.unbosque.model.MovilDTO;
import co.edu.unbosque.model.AudifonoDTO;

import co.edu.unbosque.model.LabialDTO;
import co.edu.unbosque.model.PestaninaDTO;
import co.edu.unbosque.model.JuegoMesaDTO;
import co.edu.unbosque.model.ColegioDTO;
import co.edu.unbosque.model.OficinaDTO;
import co.edu.unbosque.model.HombreDTO;
import co.edu.unbosque.model.MujerDTO;

/**
 * Capa de datos: expone “información” a la UI.
 * - Lista entidades ligeras para pintar.
 * - Ejecuta operaciones de datos (eliminar por nombre).
 * No contiene lógica de UI.
 */
@Named("principalService")
@ApplicationScoped
public class PrincipalService {

private ModelFacade mf;
	
	public PrincipalService() {
		mf = new ModelFacade();
		// TODO Auto-generated constructor stub
	}
	
	 public void crearAu(AudifonoDTO eliminar) {
		 
	        mf.getAudifonoDAO().delete(eliminar);
	    }

	    public void crearMo(MovilDTO eliminar) {
	        mf.getMovilDAO().delete(eliminar);
	    }

	    public void crearLa(LabialDTO eliminar) {
	        mf.getLabialDAO().delete(eliminar);
	    }

	    public void crearPes(PestaninaDTO eliminar) {
	        mf.getPestaninaDAO().delete(eliminar);
	    }

	    public void crearJue(JuegoMesaDTO eliminar) {
	        mf.getJuegoMesaDAO().delete(eliminar);
	    }

	    public void crearCo(ColegioDTO eliminar) {
	        mf.getColegioDAO().delete(eliminar);
	    }

	    public void crearOfi(OficinaDTO eliminar) {
	       mf.getOficinaDAO().delete(eliminar);
	    }

	    public void crearHom(HombreDTO eliminar) {
	        mf.getHombreDAO().delete(eliminar);
	    }

	    public void crearMuj(MujerDTO eliminar) {
	        mf.getMujerDAO().delete(eliminar);
	    }

}
