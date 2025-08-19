package co.edu.unbosque.service;

import co.edu.unbosque.model.CarritoDTO;
import co.edu.unbosque.model.ModelFacade;

public class CarritoService {
	public CarritoService() {
		// TODO Auto-generated constructor stub
	}
	 public void elimiaPpoducto(CarritoDTO elimiar) {
	        ModelFacade.carritoDAO.delete(elimiar);
	    }

}
