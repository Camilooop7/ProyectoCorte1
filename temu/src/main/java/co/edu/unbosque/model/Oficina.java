package co.edu.unbosque.model;

import java.io.Serializable;

public class Oficina extends Papeleria implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean esDecorativo;

	public Oficina() {
		// TODO Auto-generated constructor stub
	}

	public Oficina(boolean esDecorativo) {
		super();
		this.esDecorativo = esDecorativo;
	}

	public Oficina(String nombre, int precio, String imagen, int cantidadPaquete, boolean esDecorativo) {
		super(nombre, precio, imagen, cantidadPaquete);
		this.esDecorativo = esDecorativo;
	}

	public Oficina(String nombre, int precio, String imagen, int cantidadPaquete) {
		super(nombre, precio, imagen, cantidadPaquete);
		// TODO Auto-generated constructor stub
	}

	public boolean isEsDecorativo() {
		return esDecorativo;
	}

	public void setEsDecorativo(boolean esDecorativo) {
		this.esDecorativo = esDecorativo;
	}

	@Override
	public String toString() {
		return super.toString() + "Oficina [esDecorativo=" + esDecorativo + "]";
	}

}
