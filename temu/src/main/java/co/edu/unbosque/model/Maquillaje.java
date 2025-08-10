package co.edu.unbosque.model;

import java.io.Serializable;

public abstract class Maquillaje extends Producto implements Serializable {
	private static final long serialVersionUID = 1L;
	/** Atributo de maquillaje. */
	private boolean esApruebaDeAgua;

	public Maquillaje() {
		// TODO Auto-generated constructor stub
	}

	public Maquillaje(boolean esApruebaDeAgua) {
		super();
		this.esApruebaDeAgua = esApruebaDeAgua;
	}

	public Maquillaje(String nombre, int precio, String imagen, boolean esApruebaDeAgua) {
		super(nombre, precio, imagen);
		this.esApruebaDeAgua = esApruebaDeAgua;
	}

	public Maquillaje(String nombre, int precio, String imagen) {
		super(nombre, precio, imagen);
		// TODO Auto-generated constructor stub
	}

	public boolean isEsApruebaDeAgua() {
		return esApruebaDeAgua;
	}

	public void setEsApruebaDeAgua(boolean esApruebaDeAgua) {
		this.esApruebaDeAgua = esApruebaDeAgua;
	}

	@Override
	public String toString() {
		return super.toString() + "Maquillaje [esApruebaDeAgua=" + esApruebaDeAgua + "]";
	}

}
