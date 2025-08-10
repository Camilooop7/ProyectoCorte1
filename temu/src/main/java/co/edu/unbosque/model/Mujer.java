package co.edu.unbosque.model;

import java.io.Serializable;

public class Mujer extends Ropa implements Serializable {
	private boolean esConjunto;

	public Mujer() {
		// TODO Auto-generated constructor stub
	}

	public Mujer(boolean esConjunto) {
		super();
		this.esConjunto = esConjunto;
	}

	public Mujer(String nombre, int precio, int id, String fecha, String imagen, int talla, String color,
			boolean esConjunto) {
		super(nombre, precio, id, fecha, imagen, talla, color);
		this.esConjunto = esConjunto;
	}

	public Mujer(String nombre, int precio, int id, String fecha, String imagen, int talla, String color) {
		super(nombre, precio, id, fecha, imagen, talla, color);
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
