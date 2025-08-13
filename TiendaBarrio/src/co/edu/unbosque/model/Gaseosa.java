package co.edu.unbosque.model;

import java.io.Serializable;

public class Gaseosa extends Bebida implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean esZero;

	public Gaseosa() {
		// TODO Auto-generated constructor stub
	}

	public Gaseosa(boolean esZero) {
		super();
		this.esZero = esZero;
	}

	public Gaseosa(String nombre, int precio, String imagen, String presentacion, boolean esZero) {
		super(nombre, precio, imagen, presentacion);
		this.esZero = esZero;
	}

	public Gaseosa(String nombre, int precio, String imagen, String presentacion) {
		super(nombre, precio, imagen, presentacion);
		// TODO Auto-generated constructor stub
	}

	public boolean isEsZero() {
		return esZero;
	}

	public void setEsZero(boolean esZero) {
		this.esZero = esZero;
	}

	@Override
	public String toString() {
		return super.toString() + "Gaseosa [esZero=" + esZero + "]";
	}

}
