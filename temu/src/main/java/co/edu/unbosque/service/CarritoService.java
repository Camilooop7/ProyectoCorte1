package co.edu.unbosque.service;

import co.edu.unbosque.model.CarritoDTO;
import co.edu.unbosque.model.ModelFacade;

public class CarritoService {
	private ModelFacade mf;
	public CarritoService() {
		mf = new ModelFacade();
		// TODO Auto-generated constructor stub
	}
	 public void elimiaPpoducto(CarritoDTO elimiar) {
	        mf.getCarritoDAO().delete(elimiar);
	    }

}
