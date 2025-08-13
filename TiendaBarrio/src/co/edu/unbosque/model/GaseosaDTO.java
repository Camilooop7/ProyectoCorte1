package co.edu.unbosque.model;

import java.io.Serializable;

public class GaseosaDTO extends Bebida implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean esZero;

	public GaseosaDTO() {
		// TODO Auto-generated constructor stub
	}

	public GaseosaDTO(boolean esZero) {
		super();
		this.esZero = esZero;
	}

	public GaseosaDTO(String nombre, int precio, String imagen, String presentacion, boolean esZero) {
		super(nombre, precio, imagen, presentacion);
		this.esZero = esZero;
	}

	public GaseosaDTO(String nombre, int precio, String imagen, String presentacion) {
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
