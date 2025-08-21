package co.edu.unbosque.model;

import java.io.Serializable;

public class HombreDTO extends Ropa implements Serializable {
	private static final long serialVersionUID = 1L;
	private String esDeportiva;

	public HombreDTO() {
		// TODO Auto-generated constructor stub
	}

	public HombreDTO(String esDeportiva) {
		super();
		this.esDeportiva = esDeportiva;
	}

	public HombreDTO(String nombre, int precio, String imagen, String talla, String color,
			String esDeportiva) {
		super(nombre, precio, imagen, talla, color);
		this.esDeportiva = esDeportiva;
	}

	public HombreDTO(String nombre, int precio, String imagen, String talla, String color) {
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
