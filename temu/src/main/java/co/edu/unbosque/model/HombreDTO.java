package co.edu.unbosque.model;

import java.io.Serializable;

public class HombreDTO extends Ropa implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean esDeportiva;

	public HombreDTO() {
		// TODO Auto-generated constructor stub
	}

	public HombreDTO(boolean esDeportiva) {
		super();
		this.esDeportiva = esDeportiva;
	}

	public HombreDTO(String nombre, int precio,  String imagen, int talla, String color,
			boolean esDeportiva) {
		super(nombre, precio,  imagen, talla, color);
		this.esDeportiva = esDeportiva;
	}

	public HombreDTO(String nombre, int precio,  String imagen, int talla, String color) {
		super(nombre, precio, imagen, talla, color);
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
