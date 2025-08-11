package co.edu.unbosque.model;

import java.io.Serializable;

public class MujerDTO extends Ropa implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean esConjunto;

	public MujerDTO() {
		// TODO Auto-generated constructor stub
	}

	public MujerDTO(boolean esConjunto) {
		super();
		this.esConjunto = esConjunto;
	}

	public MujerDTO(String nombre, int precio,  String imagen, int talla, String color,
			boolean esConjunto) {
		super(nombre, precio, imagen, talla, color);
		this.esConjunto = esConjunto;
	}

	public MujerDTO(String nombre, int precio,  String imagen, int talla, String color) {
		super(nombre, precio,  imagen, talla, color);
		// TODO Auto-generated constructor stub
	}

	public boolean isEsConjunto() {
		return esConjunto;
	}

	public void setEsConjunto(boolean esConjunto) {
		this.esConjunto = esConjunto;
	}

	@Override
	public String toString() {
		return super.toString()+"Mujer [esConjunto=" + esConjunto + "]";
	}

}
