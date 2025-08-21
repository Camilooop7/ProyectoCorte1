package co.edu.unbosque.model;

import java.io.Serializable;

public class MujerDTO extends Ropa implements Serializable {
	private static final long serialVersionUID = 1L;
	private String esConjunto;

	public MujerDTO() {
		// TODO Auto-generated constructor stub
	}

	public MujerDTO(String esConjunto) {
		super();
		this.esConjunto = esConjunto;
	}

	public MujerDTO(String nombre, int precio,  String imagen, String talla, String color,
			String esConjunto) {
		super(nombre, precio, imagen, talla, color);
		this.esConjunto = esConjunto;
	}

	public MujerDTO(String nombre, int precio,  String imagen, String talla, String color) {
		super(nombre, precio,  imagen, talla, color);
		// TODO Auto-generated constructor stub
	}

	public String isEsConjunto() {
		return esConjunto;
	}

	public void setEsConjunto(String esConjunto) {
		this.esConjunto = esConjunto;
	}

	@Override
	public String toString() {
		return super.toString()+"Mujer [esConjunto=" + esConjunto + "]";
	}

}
