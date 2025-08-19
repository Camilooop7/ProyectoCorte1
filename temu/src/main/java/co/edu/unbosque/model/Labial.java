package co.edu.unbosque.model;

import java.io.Serializable;

public class Labial extends Maquillaje implements Serializable {
	private static final long serialVersionUID = 1L;
	private String tono;

	public Labial() {
		// TODO Auto-generated constructor stub
	}

	public Labial(String tono) {
		super();
		this.tono = tono;
	}

	public Labial(String nombre, int precio, String imagen, String esApruebaDeAgua, String tono) {
		super(nombre, precio, imagen, esApruebaDeAgua);
		this.tono = tono;
	}

	public Labial(String nombre, int precio, String imagen, String esApruebaDeAgua) {
		super(nombre, precio, imagen, esApruebaDeAgua);
		// TODO Auto-generated constructor stub
	}

	public String getTono() {
		return tono;
	}

	public void setTono(String tono) {
		this.tono = tono;
	}

	/**
	 * Representa el objeto Labial como una cadena de texto.
	 * 
	 * @return Cadena de texto que representa al labial.
	 */
	@Override
	public String toString() {
		return super.toString() + "Labial [tono=" + tono + "]";
	}

}
