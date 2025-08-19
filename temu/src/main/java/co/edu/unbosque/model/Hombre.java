package co.edu.unbosque.model;

import java.io.Serializable;

public class Hombre extends Ropa implements Serializable {
	private static final long serialVersionUID = 1L;
	private String esDeportiva;

	public Hombre() {
		// TODO Auto-generated constructor stub
	}

	public Hombre(String esDeportiva) {
		super();
		this.esDeportiva = esDeportiva;
	}

	public Hombre(String nombre, int precio, String imagen, String talla, String color,
			String esDeportiva) {
		super(nombre, precio, imagen, talla, color);
		this.esDeportiva = esDeportiva;
	}

	public Hombre(String nombre, int precio, String imagen, String talla, String color) {
		super(nombre, precio, imagen, talla, color);
		// TODO Auto-generated constructor stub
	}


	public String getEsDeportiva() {
		return esDeportiva;
	}

	public void setEsDeportiva(String esDeportiva) {
		this.esDeportiva = esDeportiva;
	}

	@Override
	public String toString() {
		return super.toString()+"Hombre [esDeportiva=" + esDeportiva + "]";
	}

}
