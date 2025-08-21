package co.edu.unbosque.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Historial implements Serializable {
	private String carro;
	private ArrayList<String> listaHitorial;
	
	public Historial() {
	listaHitorial= new ArrayList<>();
		// TODO Auto-generated constructor stub
	}

	public Historial(String carro, ArrayList<String> listaHitorial) {
		super();
		this.carro = carro;
		this.listaHitorial = listaHitorial;
	}

	


	public String getCarro() {
		return carro;
	}

	public void setCarro(String carro) {
		this.carro = carro;
	}

	public ArrayList<String> getListaHitorial() {
		return listaHitorial;
	}

	public void setListaHitorial(ArrayList<String> listaHitorial) {
		this.listaHitorial = listaHitorial;
	}

	@Override
	public String toString() {
		return "Historial [carro=" + carro + ", listaHitorial=" + listaHitorial + "]";
	}
	
	
	

}
