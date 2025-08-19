package co.edu.unbosque.service;

import co.edu.unbosque.model.AudifonoDTO;
import co.edu.unbosque.model.ColegioDTO;
import co.edu.unbosque.model.EducativoDTO;
import co.edu.unbosque.model.HombreDTO;
import co.edu.unbosque.model.JuegoMesaDTO;
import co.edu.unbosque.model.LabialDTO;
import co.edu.unbosque.model.ModelFacade;
import co.edu.unbosque.model.MovilDTO;
import co.edu.unbosque.model.MujerDTO;
import co.edu.unbosque.model.OficinaDTO;
import co.edu.unbosque.model.PestaninaDTO;

public class AddService {
	private ModelFacade mf;

    public AddService() {
    	mf = new ModelFacade();
        // TODO Auto-generated constructor stub
    }

	 public void crearAu(AudifonoDTO eliminar) {
		 
	        mf.getAudifonoDAO().add(eliminar);
	        System.out.println("añadido");
	        System.out.println(mf.getAudifonoDAO().toString());
	        System.out.println(mf.getAudifonoDAO().getListaAudifono().size());
	    }

	    public void crearMo(MovilDTO eliminar) {
	        mf.getMovilDAO().add(eliminar);
	    }

	    public void crearLa(LabialDTO eliminar) {
	        mf.getLabialDAO().add(eliminar);
	    }

	    public void crearPes(PestaninaDTO eliminar) {
	        mf.getPestaninaDAO().add(eliminar);
	    }

	    public void crearJue(JuegoMesaDTO eliminar) {
	        mf.getJuegoMesaDAO().add(eliminar);
	    }

	    public void crearCo(ColegioDTO eliminar) {
	        mf.getColegioDAO().add(eliminar);
	    }

	    public void crearOfi(OficinaDTO eliminar) {
	       mf.getOficinaDAO().add(eliminar);
	    }

	    public void crearHom(HombreDTO eliminar) {
	        mf.getHombreDAO().add(eliminar);
	    }

	    public void crearMuj(MujerDTO eliminar) {
	        mf.getMujerDAO().add(eliminar);
	    }
}
