package co.edu.unbosque.model;

import java.io.Serializable;

public class ColegioDTO extends Papeleria implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean esSeguro;

	public ColegioDTO() {
		// TODO Auto-generated constructor stub
	}

	public ColegioDTO(boolean esSeguro) {
		super();
		this.esSeguro = esSeguro;
	}

	public ColegioDTO(String nombre, int precio, String imagen, int cantidadPaquete, boolean esSeguro) {
		super(nombre, precio, imagen, cantidadPaquete);
		this.esSeguro = esSeguro;
	}

	public ColegioDTO(String nombre, int precio, String imagen, int cantidadPaquete) {
		super(nombre, precio, imagen, cantidadPaquete);
		// TODO Auto-generated constructor stub
	}

	public boolean isEsSeguro() {
		return esSeguro;
	}

	public void setEsSeguro(boolean esSeguro) {
		this.esSeguro = esSeguro;
	}

	@Override
	public String toString() {
		return super.toString() + "Colegio [esSeguro=" + esSeguro + "]";
	}

}
