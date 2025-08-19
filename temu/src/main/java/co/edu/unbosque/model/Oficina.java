package co.edu.unbosque.model;

import java.io.Serializable;

public class Oficina extends Papeleria implements Serializable {
	private static final long serialVersionUID = 1L;
	private String esDecorativo;

	public Oficina() {
		// TODO Auto-generated constructor stub
	}

	public Oficina(String esDecorativo) {
		super();
		this.esDecorativo = esDecorativo;
	}

	public Oficina(String nombre, int precio, String imagen, String cantidadPaquete, String esDecorativo) {
		super(nombre, precio, imagen, cantidadPaquete);
		this.esDecorativo = esDecorativo;
	}

	public Oficina(String nombre, int precio, String imagen, String cantidadPaquete) {
		super(nombre, precio, imagen, cantidadPaquete);
		// TODO Auto-generated constructor stub
	}

	public String isEsDecorativo() {
		return esDecorativo;
	}

	public void setEsDecorativo(String esDecorativo) {
		this.esDecorativo = esDecorativo;
	}

	@Override
	public String toString() {
		return super.toString() + "Oficina [esDecorativo=" + esDecorativo + "]";
	}

}
