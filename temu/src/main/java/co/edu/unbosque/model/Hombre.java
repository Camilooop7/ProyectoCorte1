package co.edu.unbosque.model;

import java.io.Serializable;

public class Hombre extends Ropa implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean esDeportiva;

	public Hombre() {
		// TODO Auto-generated constructor stub
	}

	public Hombre(boolean esDeportiva) {
		super();
		this.esDeportiva = esDeportiva;
	}

	public Hombre(String nombre, int precio, int id, String fecha, String imagen, int talla, String color,
			boolean esDeportiva) {
		super(nombre, precio, id, fecha, imagen, talla, color);
		this.esDeportiva = esDeportiva;
	}

	public Hombre(String nombre, int precio, int id, String fecha, String imagen, int talla, String color) {
		super(nombre, precio, id, fecha, imagen, talla, color);
		// TODO Auto-generated constructor stub
	}

	public boolean isEsDeportiva() {
		return esDeportiva;
	}

	public void setEsDeportiva(boolean esDeportiva) {
		this.esDeportiva = esDeportiva;
	}

	@Override
	public String toString() {
		return super.toString()+"Hombre [esDeportiva=" + esDeportiva + "]";
	}

}
