package co.edu.unbosque.model;

import java.io.Serializable;

public class Colegio extends Papeleria implements Serializable {
	private static final long serialVersionUID = 1L;
	private String esSeguro;

	public Colegio() {
		// TODO Auto-generated constructor stub
	}

	public Colegio(String esSeguro) {
		super();
		this.esSeguro = esSeguro;
	}

	public Colegio(String nombre, int precio, String imagen, String cantidadPaquete, String esSeguro) {
		super(nombre, precio, imagen, cantidadPaquete);
		this.esSeguro = esSeguro;
	}

	public Colegio(String nombre, int precio, String imagen, String cantidadPaquete) {
		super(nombre, precio, imagen, cantidadPaquete);
		// TODO Auto-generated constructor stub
	}



	public String getEsSeguro() {
		return esSeguro;
	}

	public void setEsSeguro(String esSeguro) {
		this.esSeguro = esSeguro;
	}

	@Override
	public String toString() {
		return super.toString() + "Colegio [esSeguro=" + esSeguro + "]";
	}

}
