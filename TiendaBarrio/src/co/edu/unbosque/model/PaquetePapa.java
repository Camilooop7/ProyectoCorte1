package co.edu.unbosque.model;

import java.io.Serializable;

public class PaquetePapa extends Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	private boolean esPicante;

	public PaquetePapa() {
		// TODO Auto-generated constructor stub
	}

	public PaquetePapa(boolean esPicante) {
		super();
		this.esPicante = esPicante;
	}

	public PaquetePapa(String nombre, int precio, String imagen, boolean esPicante) {
		super(nombre, precio, imagen);
		this.esPicante = esPicante;
	}

	public PaquetePapa(String nombre, int precio, String imagen) {
		super(nombre, precio, imagen);
		// TODO Auto-generated constructor stub
	}

	public boolean isEsPicante() {
		return esPicante;
	}

	public void setEsPicante(boolean esPicante) {
		this.esPicante = esPicante;
	}

	@Override
	public String toString() {
		return super.toString() + "PaquetePapa [esPicante=" + esPicante + "]";
	}

}
