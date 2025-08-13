package co.edu.unbosque.model;

import java.io.Serializable;

public class Jugo extends Bebida implements Serializable {
	private static final long serialVersionUID = 1L;

	private boolean esNatural;

	public Jugo() {
		// TODO Auto-generated constructor stub
	}

	public Jugo(boolean esNatural) {
		super();
		this.esNatural = esNatural;
	}

	public Jugo(String nombre, int precio, String imagen, String presentacion, boolean esNatural) {
		super(nombre, precio, imagen, presentacion);
		this.esNatural = esNatural;
	}

	public Jugo(String nombre, int precio, String imagen, String presentacion) {
		super(nombre, precio, imagen, presentacion);
		// TODO Auto-generated constructor stub
	}

	public boolean isEsNatural() {
		return esNatural;
	}

	public void setEsNatural(boolean esNatural) {
		this.esNatural = esNatural;
	}

	@Override
	public String toString() {
		return super.toString() + "Jugo [esNatural=" + esNatural + "]";
	}

}
